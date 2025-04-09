package com.han.loan_simulator.infrastructure.loan;

import com.han.loan_simulator.domain.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanJpaRepository extends JpaRepository<Loan, Long> {

}
