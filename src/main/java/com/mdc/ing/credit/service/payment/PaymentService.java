package com.mdc.ing.credit.service.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.constants.GeneralConstants;
import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.model.exception.IngBusinessException;
import com.mdc.ing.credit.model.exception.IngFraudException;
import com.mdc.ing.credit.model.input.CreatePaymentPlanInput;
import com.mdc.ing.credit.model.request.PayLoanRequest;
import com.mdc.ing.credit.model.response.PayLoanResponse;
import com.mdc.ing.credit.service.core.LoanCoreService;
import com.mdc.ing.credit.service.core.LoanInstallmentCoreService;
import com.mdc.ing.credit.service.customer.CustomerService;
import com.mdc.ing.credit.service.loan.LoanInstallmentService;
import com.mdc.ing.credit.service.security.FraudService;
import com.mdc.ing.credit.service.user.IngUserService;
import com.mdc.ing.credit.util.LocalDateTimeCalculator;
import com.mdc.ing.credit.util.LocalDateTimeComparator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final LoanInstallmentService loanInstallmentService;
	
	private final LoanCoreService loanCoreService;
	private final IngUserService ingUserService;
	private final FraudService fraudService;
	private final CustomerService customerService;
	private final LoanInstallmentCoreService loanInstallmentCoreService;
	
	public void createPaymentPlan(CreatePaymentPlanInput createPaymentPlanInput) {
		loanInstallmentService.createLoanPaymentList(createPaymentPlanInput);
	}
	
	public PayLoanResponse payLoan(PayLoanRequest payLoanRequest) {
		IngUser ingUser = ingUserService.getLoggedInUser();
		fraudService.fraudCheck(ingUser);
		
		Customer customer = customerService.findCustomerOfUser(ingUser);
		
		if (customer == null) {
			throw new IngBusinessException();
		}
		
		List<Loan> loans = loanCoreService.getLoansOfCustomer(customer);
		
		// Filter on only current user's loans, so we can't see other people's loan data.
		Loan selectedLoan = loans
				.stream()
				.filter(loan -> loan.getId().equals(payLoanRequest.getLoanId()))
				.findFirst()
				.orElse(null);
		
		if (selectedLoan == null) {
			throw new IngFraudException();
		}
		
		List<LoanInstallment> selectedInstallmentList = loanInstallmentCoreService.getLoanPaymentList(selectedLoan);
		Collections.sort(selectedInstallmentList, new LocalDateTimeComparator());
		
		BigDecimal currentAmount = payLoanRequest.getAmount();
		
		Integer numberOfPaidInstallments = 0;
		BigDecimal totalAmountSpent = BigDecimal.ZERO;
		Boolean hasLoanBeenPaidInFull = Boolean.FALSE;
		
		// This method can be called around 12:00 at night on the last day of the month.
		for(LoanInstallment loanInstallment : selectedInstallmentList) {
			if (!LocalDateTimeCalculator.isDifferenceInMonthsBetweenDatesValid(LocalDateTime.now(), loanInstallment.getDueDate())) {
				break;				
			}
			
			if (!Boolean.FALSE.equals(loanInstallment.getIsPaid())) {
				continue;
			}
			
			BigDecimal loanAmount = loanInstallment.getAmount();
			
			if (currentAmount.compareTo(loanAmount) == -1) {
				break;
			}
			
			loanInstallment.setPaidAmount(loanInstallment.getPaidAmount().add(loanAmount, GeneralConstants.PROJECT_MATH_CONTEXT));
			loanInstallment.setPaymentDate(LocalDateTime.now());
			loanInstallment.setIsPaid(Boolean.TRUE);
			
			loanInstallmentCoreService.updateLoanPayment(loanInstallment);
			
			currentAmount = currentAmount.subtract(loanAmount, GeneralConstants.PROJECT_MATH_CONTEXT);
			totalAmountSpent = totalAmountSpent.add(loanAmount, GeneralConstants.PROJECT_MATH_CONTEXT);
			numberOfPaidInstallments++;
		}
		
		hasLoanBeenPaidInFull = numberOfPaidInstallments.equals(selectedInstallmentList.size());
		
		if (hasLoanBeenPaidInFull) {
			selectedLoan.setIsPaid(hasLoanBeenPaidInFull);
			loanCoreService.createOrUpdateLoan(selectedLoan);
		}
		
		return PayLoanResponse
				.builder()
				.numberOfPaidInstallments(numberOfPaidInstallments)
				.totalAmountSpent(totalAmountSpent)
				.hasLoanBeenPaidInFull(hasLoanBeenPaidInFull)
				.build();
	}
}
