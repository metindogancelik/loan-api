package com.mdc.ing.credit.service.core;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.repository.LoanInstallmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanInstallmentCoreService {
	
	private final LoanInstallmentRepository loanInstallmentRepository;
	
	public List<LoanInstallment> createOrUpdateLoanPaymentList(List<LoanInstallment> loanInstallmentList) {
		return loanInstallmentRepository.saveAll(loanInstallmentList);
	}
	
	public List<LoanInstallment> getLoanPaymentList(Loan loan) {
		return loanInstallmentRepository.findAllByLoan(loan).orElse(Collections.emptyList());
	}
	
	public LoanInstallment updateLoanPayment(LoanInstallment loanInstallment) {
		return loanInstallmentRepository.save(loanInstallment);
	}
}
