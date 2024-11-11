package com.mdc.ing.credit.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mdc.ing.credit.model.base.BaseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoanInstallmentDTO extends BaseDTO {
	
	private static final long serialVersionUID = -4947946983258099624L;

    private Long installmentId;
    private Long relatedLoanId;
    private BigDecimal installmentAmount;
    private BigDecimal paidAmount;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private Boolean isPaid;
}
