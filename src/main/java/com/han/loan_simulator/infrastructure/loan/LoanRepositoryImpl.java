package com.han.loan_simulator.infrastructure.loan;

import com.han.loan_simulator.domain.loan.Loan;
import com.han.loan_simulator.domain.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanRepositoryImpl implements LoanRepository {
    private final LoanJpaRepository jpaRepository;

    @Override
    public Loan save(Loan loan) {
        return jpaRepository.save(loan);
    }
}
