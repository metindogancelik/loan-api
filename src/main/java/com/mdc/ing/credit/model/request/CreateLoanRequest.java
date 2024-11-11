package com.mdc.ing.credit.model.request;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.base.BaseRequest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanRequest extends BaseRequest {

	private static final long serialVersionUID = 86032792565231575L;
	
    @NotNull(message = "Customer name is required.")
    private String name;
    
    @NotNull(message = "Customer surname is required.")
    private String surname;

	@NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero.")
    private BigDecimal amount;
	
	@NotNull(message = "Interest rate is required.")
    // Minimum and maximum interest rates are should be dynamic.
    private BigDecimal interestRate;

    @NotNull(message = "Number of installments is required.")
    @Min(value = 1, message = "Number of installments must be at least 1.")
    private Integer numberOfInstallments;
}
