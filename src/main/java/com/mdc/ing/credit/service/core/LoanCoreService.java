package com.mdc.ing.credit.service.core;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanCoreService {
	
	private final LoanRepository loanRepository;
	
	public void createOrUpdateLoan(Loan loan) {
		loanRepository.save(loan);
	}
	
	public List<Loan> getLoansOfCustomer(Customer customer) {
		return loanRepository.findLoanByCustomer(customer).orElse(Collections.emptyList());
	}
}
