package com.mdc.ing.credit.util;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.mdc.ing.credit.model.entity.LoanInstallment;
import com.mdc.ing.credit.model.exception.IngBusinessException;

public class LocalDateTimeComparator implements Comparator<LoanInstallment> {

    @Override
    public int compare(LoanInstallment liOne, LoanInstallment liTwo) {
        if (liOne == null || liTwo == null) {
            throw new IngBusinessException();
        }

        if (liOne instanceof LoanInstallment && liTwo instanceof LoanInstallment) {
            LocalDateTime dateTimeOne = ((LoanInstallment) liOne).getDueDate();
            LocalDateTime dateTimeTwo = ((LoanInstallment) liTwo).getDueDate();
            
            return dateTimeOne.compareTo(dateTimeTwo);
        }

        throw new IngBusinessException();
    }
}
