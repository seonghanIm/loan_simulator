package com.han.loan_simulator.web.loan;

import com.han.loan_simulator.domain.loan.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanRequest {
    private String nickName;
    private Long loanProductId;
    private BigDecimal principal;
    private int termMonths;
    private LocalDate startDate;
}
