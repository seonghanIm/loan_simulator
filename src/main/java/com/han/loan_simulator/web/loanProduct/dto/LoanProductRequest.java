package com.han.loan_simulator.web.loanProduct.dto;

import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoanProductRequest {
    private String name;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private int minTermMonth;
    private int maxTermMonth;
    private BigDecimal interestRate;
    private RepaymentMethod repaymentMethod;


    public LoanProduct toEntity(){
        return LoanProduct.builder()
                .name(name)
                .minAmount(minAmount)
                .maxAmount(maxAmount)
                .minTermMonth(minTermMonth)
                .maxTermMonth(maxTermMonth)
                .interestRate(interestRate)
                .repaymentMethod(repaymentMethod)
                .build();
    }
}
