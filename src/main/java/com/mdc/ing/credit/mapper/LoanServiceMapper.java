package com.mdc.ing.credit.mapper;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.model.input.CreateCustomerInput;
import com.mdc.ing.credit.model.input.CreatePaymentPlanInput;
import com.mdc.ing.credit.model.request.CreateLoanRequest;
import com.mdc.ing.credit.model.response.LoanDTO;
import com.mdc.ing.credit.model.response.LoanInstallmentDTO;

public abstract class LoanServiceMapper {
	private static final SecureRandom secureRandom = new SecureRandom();
	
	public static CreateCustomerInput createCustomerInput(IngUser ingUser, CreateLoanRequest createLoanRequest) {
		return CreateCustomerInput
				.builder()
				.ingUser(ingUser)
				.name(createLoanRequest.getName())
				.surname(createLoanRequest.getSurname())
				.creditLimit(BigDecimal.valueOf(secureRandom.nextInt(1000000))) // Let's assume this value comes from the Credit Department service.
				.usedCreditLimit(BigDecimal.ZERO)
				.build();
	}
	
	public static Loan createLoanModel(Customer customer, CreateLoanRequest createLoanRequest) {
		return Loan
				.builder()
				.customer(customer)
				.loanAmount(createLoanRequest.getAmount())
				.numberOfInstallments(createLoanRequest.getNumberOfInstallments())
				.createDate(LocalDateTime.now())
				.isPaid(Boolean.FALSE)
				.build();
	}
	
	public static CreatePaymentPlanInput createPaymentPlanInput(Loan relatedLoan, Integer numberOfInstallments, BigDecimal eachInstallmentAmount) {
		return CreatePaymentPlanInput
				.builder()
				.relatedLoan(relatedLoan)
				.numberOfInstallments(numberOfInstallments)
				.eachInstallmentAmount(eachInstallmentAmount)
				.build();
	}
	
	public static LoanDTO createLoanDTO(Loan loan) {
		return LoanDTO
				.builder()
				.loanId(loan.getId())
				.loanAmount(loan.getLoanAmount())
				.numberOfInstallments(loan.getNumberOfInstallments())
				.createDate(loan.getCreateDate())
				.isPaid(loan.getIsPaid())
				.build();
	}
	
	public static LoanInstallmentDTO createLoanInstallmentDTO(LoanInstallment loanInstallment) {
		return LoanInstallmentDTO
				.builder()
			    .installmentId(loanInstallment.getId())
			    .relatedLoanId(loanInstallment.getLoan().getId())
			    .installmentAmount(loanInstallment.getAmount())
			    .paidAmount(loanInstallment.getPaidAmount())
			    .dueDate(loanInstallment.getDueDate())
			    .paymentDate(loanInstallment.getPaymentDate())
			    .isPaid(loanInstallment.getIsPaid())
				.build();
	}
}
