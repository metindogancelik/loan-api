package com.mdc.ing.credit.model.response;

import java.util.List;

import com.mdc.ing.credit.model.base.BaseResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListLoansResponse extends BaseResponse {
	
	private static final long serialVersionUID = -4947946983258099624L;
	private List<LoanDTO> loanList;
}
