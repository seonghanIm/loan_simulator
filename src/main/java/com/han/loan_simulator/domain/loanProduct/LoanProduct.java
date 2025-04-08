package com.han.loan_simulator.domain.loanProduct;

import com.han.loan_simulator.web.loanProduct.dto.LoanProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LoanProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="min_amount")
    private BigDecimal minAmount;

    @Column(name="max_amount")
    private BigDecimal maxAmount;

    @Column(name="min_term_month")
    private int minTermMonth;

    @Column(name="max_term_month")
    private int maxTermMonth;

    @Column(name="interest_rate")
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name="repayment_method")
    private RepaymentMethod repaymentMethod;


    public boolean isValidAmount(){
        int result = minAmount.compareTo(maxAmount);
        if(result == 1) return false;
        return true;
    }

    public boolean isValidTerm(){
        if(minTermMonth > maxTermMonth) return false;
        return true;
    }

    public LoanProductResponse toResponse(){
        return LoanProductResponse.builder()
                .loanProductId(id)
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
