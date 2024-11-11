package com.mdc.ing.credit.model.input;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.entity.IngUser;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCustomerInput {
	@NotNull
	private IngUser ingUser;
	
	@NotNull
    private String name;
    
    @NotNull
    private String surname;
    
    @NotNull
    private BigDecimal creditLimit;
    
    @NotNull
    private BigDecimal usedCreditLimit;
}
