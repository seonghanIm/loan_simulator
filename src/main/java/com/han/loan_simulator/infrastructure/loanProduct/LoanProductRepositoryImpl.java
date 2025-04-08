package com.han.loan_simulator.infrastructure.loanProduct;

import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LoanProductRepositoryImpl implements LoanProductRepository {
    private final LoanProductJpaRepository jpaRepository;
    @Override
    public Page<LoanProduct> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public Optional<LoanProduct> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public LoanProduct save(LoanProduct loanProduct) {
        return jpaRepository.save(loanProduct);
    }
}