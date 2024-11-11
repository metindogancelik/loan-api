package com.mdc.ing.credit.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.mdc.ing.credit.model.constants.GeneralConstants;
import com.mdc.ing.credit.model.exception.IngBusinessException;

public abstract class LocalDateTimeCalculator {

	public static long getDifferenceInMonthsBetweenDates(LocalDateTime dateOne, LocalDateTime dateTwo) {
		return ChronoUnit.MONTHS.between(dateOne, dateTwo);
	}
	
	public static boolean isDifferenceInMonthsBetweenDatesValid(LocalDateTime currentDate, LocalDateTime loanDate) {
		long difference = getDifferenceInMonthsBetweenDates(currentDate, loanDate);
		
		if (difference < 0) {
			throw new IngBusinessException();
		}
		
		return difference < GeneralConstants.MAXIMUM_CALENDAR_MONTH_PAYABLE;
	}
}
