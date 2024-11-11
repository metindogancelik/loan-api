package com.mdc.ing.credit.service.loan;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.mapper.LoanServiceMapper;
import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.exception.IngBusinessException;
import com.mdc.ing.credit.model.request.ListLoansRequest;
import com.mdc.ing.credit.model.response.ListLoansResponse;
import com.mdc.ing.credit.model.response.LoanDTO;
import com.mdc.ing.credit.service.core.LoanCoreService;
import com.mdc.ing.credit.service.customer.CustomerService;
import com.mdc.ing.credit.service.security.FraudService;
import com.mdc.ing.credit.service.user.IngUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListLoansService {

	private final LoanCoreService loanCoreService;
	private final IngUserService ingUserService;
	private final FraudService fraudService;
	private final CustomerService customerService;
	
	public ListLoansResponse listLoans(ListLoansRequest listLoansRequest) {
		IngUser ingUser = ingUserService.getLoggedInUser();
		fraudService.fraudCheck(ingUser);
		
		Customer customer = customerService.findCustomerOfUser(ingUser);
		
		if (customer == null) {
			throw new IngBusinessException();
		}
		
		List<Loan> loans = loanCoreService.getLoansOfCustomer(customer);
		
		Predicate<LoanDTO> numberOfInstallmentFilter = loan -> true;
		Predicate<LoanDTO> isPaidFilter = loan -> true;
		
		if (listLoansRequest != null) {
			Integer numberOfInstallments = listLoansRequest.getNumberOfInstallments();
		    Boolean isPaid = listLoansRequest.getIsPaid();
		    
		    numberOfInstallmentFilter = numberOfInstallments != null ? loan -> loan.getNumberOfInstallments().equals(numberOfInstallments) : loan -> true;
		    isPaidFilter = isPaid != null ? loan -> loan.getIsPaid().equals(isPaid) : loan -> true;	    
		}
		
		return ListLoansResponse
				.builder()
				.loanList(
					loans
						.stream()
						.map(LoanServiceMapper::createLoanDTO)
						.filter(numberOfInstallmentFilter)
						.filter(isPaidFilter)
						.collect(Collectors.toList()))
				.build();
	}
}
