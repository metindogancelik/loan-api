package com.mdc.ing.credit.service.loan;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.mapper.LoanServiceMapper;
import com.mdc.ing.credit.model.constants.Currency;
import com.mdc.ing.credit.model.constants.GeneralConstants;
import com.mdc.ing.credit.model.constants.LanguageResources;
import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.input.CreateCustomerInput;
import com.mdc.ing.credit.model.input.CreatePaymentPlanInput;
import com.mdc.ing.credit.model.request.CreateLoanRequest;
import com.mdc.ing.credit.model.response.CreateLoanResponse;
import com.mdc.ing.credit.service.core.LoanCoreService;
import com.mdc.ing.credit.service.customer.CustomerService;
import com.mdc.ing.credit.service.payment.PaymentService;
import com.mdc.ing.credit.service.security.FraudService;
import com.mdc.ing.credit.service.user.IngUserService;
import com.mdc.ing.credit.util.LoanCalculatorUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateLoanService {

	private final LoanCoreService loanCoreService;
	private final IngUserService ingUserService;
	private final FraudService fraudService;
	private final CustomerService customerService;
	private final LoanControlService loanLimitControlService;	
	private final PaymentService paymentService;
	
	public CreateLoanResponse createLoan(CreateLoanRequest createLoanRequest) {
		IngUser ingUser = ingUserService.getLoggedInUser();
		fraudService.fraudCheck(ingUser);
		
		Customer customer = customerService.findCustomerOfUser(ingUser);
		
		if (customer == null) {
			CreateCustomerInput createCustomerInput = LoanServiceMapper.createCustomerInput(ingUser, createLoanRequest);
			customer = customerService.createCustomer(createCustomerInput);
		}
		
		loanLimitControlService.checkLoan(customer, createLoanRequest);
		
		Loan loan = LoanServiceMapper.createLoanModel(customer, createLoanRequest);
		loanCoreService.createOrUpdateLoan(loan);
		
		BigDecimal requestedAmount = createLoanRequest.getAmount();
		customer.setUsedCreditLimit(customer.getUsedCreditLimit().add(requestedAmount, GeneralConstants.PROJECT_MATH_CONTEXT));
		customerService.updateCustomer(customer);
		
		Integer numberOfInstallments = createLoanRequest.getNumberOfInstallments();
		
		BigDecimal loanRepaymentAmount = LoanCalculatorUtil.calculateLoanRepaymentAmount(requestedAmount, createLoanRequest.getInterestRate());
		BigDecimal eachInstallmentAmount =  LoanCalculatorUtil.calculateEachInstallmentAmount(loanRepaymentAmount, numberOfInstallments);
		
		CreatePaymentPlanInput createPaymentPlanInput = LoanServiceMapper.createPaymentPlanInput(loan, numberOfInstallments, eachInstallmentAmount);
		
		paymentService.createPaymentPlan(createPaymentPlanInput);
		
		return CreateLoanResponse
				.builder()
				.eachInstallmentAmount(eachInstallmentAmount)
				.currency(Currency.TL) // Let's assume we are using only TL now.
				.numberOfInstallments(numberOfInstallments)
				.statusMessage(LanguageResources.LOAN_CREATED_SUCCESSFULLY_MESSAGE)
				.build();
	}
}
