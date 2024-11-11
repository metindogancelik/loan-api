package com.mdc.ing.credit.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngLoanException extends RuntimeException {

	private static final long serialVersionUID = -4782838120707178588L;
	private String exceptionMessage;
	
	public IngLoanException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}
}
