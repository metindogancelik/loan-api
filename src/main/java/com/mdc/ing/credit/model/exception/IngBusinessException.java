package com.mdc.ing.credit.model.exception;

import com.mdc.ing.credit.model.constants.LanguageResources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngBusinessException extends RuntimeException {

	private static final long serialVersionUID = 8586108196128512029L;
	private String exceptionMessage;
	
	public IngBusinessException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}
	
	public IngBusinessException() {
		super(LanguageResources.DEFAULT_BUSINESS_EXCEPTION_MESSAGE);
		this.exceptionMessage = LanguageResources.DEFAULT_BUSINESS_EXCEPTION_MESSAGE;
	}
}
