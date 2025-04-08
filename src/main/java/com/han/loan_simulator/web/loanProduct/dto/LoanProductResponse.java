package com.han.loan_simulator.web.loanProduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanProductResponse {
    public Long loanProductId;
    public String name;
    public BigDecimal minAmount;
    public BigDecimal maxAmount;
    public int minTermMonth;
    public int maxTermMonth;
    public BigDecimal interestRate;
    public RepaymentMethod repaymentMethod;



    public LoanProductResponse(Long id){
        this.loanProductId = id;
    }

    public void fromEntity(LoanProduct loanProduct){
        this.loanProductId = loanProduct.getId();
        this.name = loanProduct.getName();
        this.minAmount = loanProduct.getMinAmount();
        this.maxAmount = loanProduct.getMaxAmount();
        this.minTermMonth = loanProduct.getMinTermMonth();
        this.interestRate = loanProduct.getInterestRate();
        this.repaymentMethod = loanProduct.getRepaymentMethod();
    }

}
