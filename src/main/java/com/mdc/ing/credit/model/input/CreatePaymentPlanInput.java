package com.mdc.ing.credit.model.input;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.entity.Loan;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatePaymentPlanInput {
    @NotNull
    private Loan relatedLoan;
    
    @NotNull
    private Integer numberOfInstallments;
    
	@NotNull
    private BigDecimal eachInstallmentAmount;
}
