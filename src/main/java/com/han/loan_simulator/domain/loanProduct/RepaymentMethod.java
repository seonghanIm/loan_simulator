package com.han.loan_simulator.domain.loanProduct;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RepaymentMethod {
    EQUAL_PRINCIPAL_AND_INTEREST,
    EQUAL_PRINCIPAL,
    BULLET
}
