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
public class LoanDTO extends BaseDTO {
	
	private static final long serialVersionUID = -4947946983258099624L;

    private Long loanId;
    private BigDecimal loanAmount;
    private Integer numberOfInstallments;
    private LocalDateTime createDate;
    private Boolean isPaid;
}
