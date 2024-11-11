package com.mdc.ing.credit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdc.ing.credit.model.request.CreateLoanRequest;
import com.mdc.ing.credit.model.request.ListInstallmentsRequest;
import com.mdc.ing.credit.model.request.ListLoansRequest;
import com.mdc.ing.credit.model.request.PayLoanRequest;
import com.mdc.ing.credit.model.response.CreateLoanResponse;
import com.mdc.ing.credit.model.response.ListInstallmentsResponse;
import com.mdc.ing.credit.model.response.ListLoansResponse;
import com.mdc.ing.credit.model.response.PayLoanResponse;
import com.mdc.ing.credit.service.loan.CreateLoanService;
import com.mdc.ing.credit.service.loan.ListInstallmentsService;
import com.mdc.ing.credit.service.loan.ListLoansService;
import com.mdc.ing.credit.service.payment.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loan")
@PreAuthorize("hasRole('USER')") 
@RequiredArgsConstructor
public class LoanController {
	
	private final CreateLoanService createLoanService;
	private final ListLoansService listLoansService;
	private final ListInstallmentsService listInstallmentsService;
	private final PaymentService paymentService;

	@PostMapping("/create")
	public CreateLoanResponse createLoan(@RequestBody @Validated CreateLoanRequest createLoanRequest) {
		return createLoanService.createLoan(createLoanRequest);
	}
	
	@PostMapping("/list")
	public ListLoansResponse listLoans(@RequestBody(required=false) ListLoansRequest listLoansRequest) {
		return listLoansService.listLoans(listLoansRequest);
	}
	
	@PostMapping("/list/installment")
	public ListInstallmentsResponse listInstallment(@RequestBody @Validated ListInstallmentsRequest listInstallmentsRequest) {
		return listInstallmentsService.listInstallments(listInstallmentsRequest);
	}
	
	@PostMapping("/pay")
	public PayLoanResponse payLoan(@RequestBody @Validated PayLoanRequest payLoanRequest) {
		return paymentService.payLoan(payLoanRequest);
	}
}
