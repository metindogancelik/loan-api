package com.mdc.ing.credit.service.loan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.model.input.CreatePaymentPlanInput;
import com.mdc.ing.credit.service.core.LoanInstallmentCoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanInstallmentService {
	
	private final LoanInstallmentCoreService loanInstallmentCoreService;
	
	public List<LoanInstallment> createLoanPaymentList(CreatePaymentPlanInput createPaymentPlanInput) {
		Integer numberOfInstallments = createPaymentPlanInput.getNumberOfInstallments();
		Loan relatedLoan = createPaymentPlanInput.getRelatedLoan();
	    BigDecimal eachInstallmentAmount = createPaymentPlanInput.getEachInstallmentAmount();
	    LocalDateTime now = LocalDateTime.now();
	    List<LoanInstallment> loanInstallmentList = new ArrayList<>();
		
		IntStream.range(1, numberOfInstallments + 1).forEach(installmentNumber -> {
			LoanInstallment loanInstallment = LoanInstallment
					.builder()
					.loan(relatedLoan)
					.amount(eachInstallmentAmount)
					.paidAmount(BigDecimal.ZERO)
					.dueDate(now.plusMonths(installmentNumber).withDayOfMonth(1))
					.isPaid(Boolean.FALSE)
					.build();
			
			loanInstallmentList.add(loanInstallment);
		});

		return loanInstallmentCoreService.createOrUpdateLoanPaymentList(loanInstallmentList);
	}
	
	public List<LoanInstallment> getLoanPaymentList(Loan loan) {
		return loanInstallmentCoreService.getLoanPaymentList(loan);
	}
}
