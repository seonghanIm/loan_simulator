package com.han.loan_simulator.domain.loanProduct;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LoanProductRepository {
    LoanProduct save(LoanProduct loanProduct);
    Page<LoanProduct> findAll(Pageable pageable);
    Optional<LoanProduct> findById(Long id);
}
