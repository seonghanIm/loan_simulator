package com.han.loan_simulator.infrastructure.loanProduct;

import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoanProductJpaRepository extends JpaRepository<LoanProduct, Long> {

}
