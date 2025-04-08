package com.han.loan_simulator.domain.loanProduct;


import java.util.List;
import java.util.Optional;

public interface LoanProductRepository {
    LoanProduct save(LoanProduct loanProduct);
    List<LoanProduct> findAll();
    Optional<LoanProduct> findById(Long id);
}
