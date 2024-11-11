package com.mdc.ing.credit.model.constants;

import java.math.MathContext;
import java.math.RoundingMode;

public class GeneralConstants {
	public static final Integer MAXIMUM_CALENDAR_MONTH_PAYABLE = 3;
	public static final MathContext PROJECT_MATH_CONTEXT = new MathContext(16, RoundingMode.HALF_EVEN);
}
