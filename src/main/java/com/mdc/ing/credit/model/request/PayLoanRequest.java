package com.mdc.ing.credit.model.request;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.base.BaseRequest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayLoanRequest extends BaseRequest {

	private static final long serialVersionUID = 867973593544773135L;
	
	@NotNull(message = "Loan id is required.")
	private Long loanId;
	
	@NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero.")
    private BigDecimal amount;
}
