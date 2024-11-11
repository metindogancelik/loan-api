package com.mdc.ing.credit.util;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.constants.GeneralConstants;

public abstract class LoanCalculatorUtil {
	
	public static BigDecimal calculateLoanRepaymentAmount(BigDecimal amount, BigDecimal interestRate) {
		return amount.multiply(BigDecimal.ONE.add(interestRate), GeneralConstants.PROJECT_MATH_CONTEXT);
	}
	
	public static BigDecimal calculateEachInstallmentAmount(BigDecimal amount, Integer numberOfInstallments) {
		return amount.divide(BigDecimal.valueOf(numberOfInstallments), GeneralConstants.PROJECT_MATH_CONTEXT);
	}
}
