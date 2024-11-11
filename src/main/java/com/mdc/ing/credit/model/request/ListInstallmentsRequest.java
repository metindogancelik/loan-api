package com.mdc.ing.credit.model.request;

import com.mdc.ing.credit.model.base.BaseRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListInstallmentsRequest extends BaseRequest {

	private static final long serialVersionUID = 867973593544773135L;
	
	@NotNull(message = "Loan id is required.")
	private Long loanId;
}
