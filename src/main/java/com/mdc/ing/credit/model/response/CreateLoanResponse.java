package com.mdc.ing.credit.model.response;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.base.BaseResponse;
import com.mdc.ing.credit.model.constants.Currency;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateLoanResponse extends BaseResponse {
	
	private static final long serialVersionUID = 3648452311465462597L;
	
	private BigDecimal eachInstallmentAmount;
	private Currency currency;
    private Integer numberOfInstallments;
    private String statusMessage;
}
