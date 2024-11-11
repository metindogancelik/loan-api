package com.mdc.ing.credit.model.request;

import com.mdc.ing.credit.model.base.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListLoansRequest extends BaseRequest {

	private static final long serialVersionUID = 3073490249835333727L;
	private Integer numberOfInstallments;
    private Boolean isPaid;
}
