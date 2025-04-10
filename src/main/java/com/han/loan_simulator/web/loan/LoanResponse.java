package com.han.loan_simulator.web.loan;

import com.han.loan_simulator.domain.loan.LoanStatus;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class LoanResponse {
    private Long id;
    private String nickName;
    private Long loanProductId;
    private BigDecimal principal;
    private BigDecimal interestRate;
    private int termMonth;
    private RepaymentMethod repaymentMethod;
    private LocalDate startDate;
    private LocalDate endDate;
    private LoanStatus loanStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
