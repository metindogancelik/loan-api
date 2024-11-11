package com.mdc.ing.credit.service.loan;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mdc.ing.credit.model.constants.DynamicParameters;
import com.mdc.ing.credit.model.constants.LanguageResources;
import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.exception.IngBusinessException;
import com.mdc.ing.credit.model.exception.IngLoanException;
import com.mdc.ing.credit.model.request.CreateLoanRequest;
import com.mdc.ing.credit.service.dynamic.DynamicParameterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanControlService {
	
	private final DynamicParameterService dynamicParameterService;
	
	public void checkLoan(Customer customer, CreateLoanRequest createLoanRequest) {
		limitControl(customer, createLoanRequest);		
		installmentControl(createLoanRequest.getNumberOfInstallments());
		interestRateControl(createLoanRequest.getInterestRate());
	}

	private void limitControl(Customer customer, CreateLoanRequest createLoanRequest) {
		if ((customer.getCreditLimit().subtract(customer.getUsedCreditLimit())).compareTo(createLoanRequest.getAmount()) == -1) {
			// Let's assume we have an exception handler.
			throw new IngLoanException(LanguageResources.LOAN_NOT_ENOUGH_LIMIT_EXCEPTION_MESSAGE);
		}
	}
	
	private void installmentControl(Integer numberOfInstallments) {
		String availableInstallmentStr = dynamicParameterService.getDynamicParameter(DynamicParameters.AVAILABLE_INSTALLMENTS_FOR_LOAN_APPLICATION);
		
		if (!StringUtils.hasText(availableInstallmentStr)) {
			throw new IngBusinessException();
		}
		
		List<Integer> availableInstallmentList = null;
		
		try {
			availableInstallmentList = Arrays
					.stream(availableInstallmentStr.split(","))
					.map(part -> Integer.parseInt(part.trim()))
					.collect(Collectors.toList());
		} catch (Exception any) {
			throw new IngBusinessException();
		}
		
		if (!availableInstallmentList.contains(numberOfInstallments)) {
			throw new IngLoanException(LanguageResources.LOAN_INSTALLMENT_NUMBER_EXCEPTION_MESSAGE);
		}
	}
	
	private void interestRateControl(BigDecimal interestRate) {
		String minInterestRateStr = dynamicParameterService.getDynamicParameter(DynamicParameters.MIN_INTEREST_RATE);
		String maxInterestRateStr = dynamicParameterService.getDynamicParameter(DynamicParameters.MAX_INTEREST_RATE);
		
		if (!StringUtils.hasText(minInterestRateStr) || !StringUtils.hasText(minInterestRateStr)) {
			throw new IngBusinessException();
		}
		
		BigDecimal minInterestRate = null;
		BigDecimal maxInterestRate = null;
		
		try {
			minInterestRate = new BigDecimal(minInterestRateStr);
			maxInterestRate = new BigDecimal(maxInterestRateStr);
		} catch (Exception any) {
			throw new IngBusinessException();
		}
		
		if (minInterestRate.compareTo(interestRate) == 1 || maxInterestRate.compareTo(interestRate) == -1) {
			throw new IngLoanException(LanguageResources.LOAN_INVALID_INTEREST_RATE_EXCEPTION_MESSAGE);
		}
	}
}
