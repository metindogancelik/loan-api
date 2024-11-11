package com.mdc.ing.credit.model.response;

import java.util.List;

import com.mdc.ing.credit.model.base.BaseResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListInstallmentsResponse extends BaseResponse {

	private static final long serialVersionUID = -8518598062082244892L;
	private List<LoanInstallmentDTO> loanInstallmentList;
}
