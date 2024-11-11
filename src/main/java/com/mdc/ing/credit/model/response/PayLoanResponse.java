package com.mdc.ing.credit.model.response;

import java.math.BigDecimal;

import com.mdc.ing.credit.model.base.BaseResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PayLoanResponse extends BaseResponse {
	
	private static final long serialVersionUID = -5756482675373343410L;

	private Integer numberOfPaidInstallments;
	private BigDecimal totalAmountSpent;
	private Boolean hasLoanBeenPaidInFull;
}
