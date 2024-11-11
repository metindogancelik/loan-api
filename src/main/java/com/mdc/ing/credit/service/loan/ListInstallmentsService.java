package com.mdc.ing.credit.service.loan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.mapper.LoanServiceMapper;
import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.model.exception.IngBusinessException;
import com.mdc.ing.credit.model.exception.IngFraudException;
import com.mdc.ing.credit.model.request.ListInstallmentsRequest;
import com.mdc.ing.credit.model.response.ListInstallmentsResponse;
import com.mdc.ing.credit.service.core.LoanCoreService;
import com.mdc.ing.credit.service.core.LoanInstallmentCoreService;
import com.mdc.ing.credit.service.customer.CustomerService;
import com.mdc.ing.credit.service.security.FraudService;
import com.mdc.ing.credit.service.user.IngUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListInstallmentsService {

	private final LoanCoreService loanCoreService;
	private final IngUserService ingUserService;
	private final FraudService fraudService;
	private final CustomerService customerService;
	private final LoanInstallmentCoreService loanInstallmentCoreService;
	
	public ListInstallmentsResponse listInstallments(ListInstallmentsRequest listInstallmentsRequest) {
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
				.filter(loan -> loan.getId().equals(listInstallmentsRequest.getLoanId()))
				.findFirst()
				.orElse(null);
		
		if (selectedLoan == null) {
			throw new IngFraudException();
		}
		
		List<LoanInstallment> selectedInstallmentList = loanInstallmentCoreService.getLoanPaymentList(selectedLoan);
		
		return ListInstallmentsResponse
				.builder()
				.loanInstallmentList(
					selectedInstallmentList
						.stream()
						.map(LoanServiceMapper::createLoanInstallmentDTO)
						.collect(Collectors.toList()))
				.build();
	}
}
