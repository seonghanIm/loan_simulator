package com.han.loan_simulator.infrastructure.loanProduct;

import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanProductRepositoryImpl implements LoanProductRepository {

    private final LoanProductJpaRepository jpaRepository;

    @Override
    public LoanProduct save(LoanProduct loanProduct) {
        return jpaRepository.save(loanProduct);
    }
}
