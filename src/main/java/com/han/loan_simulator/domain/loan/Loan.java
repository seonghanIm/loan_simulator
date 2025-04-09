package com.han.loan_simulator.domain.loan;

import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import com.han.loan_simulator.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="loan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="loan_product_id", nullable = false)
    private LoanProduct loanProduct;

    // 대출 원금
    private BigDecimal principal;

    // 대출 이율
    private BigDecimal interestRate;

    // 대출 기간 (월 단위)
    private int termMonths;

    @Enumerated(EnumType.STRING)
    private RepaymentMethod repaymentMethod;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate(){
        this.createAt = LocalDateTime.now();
        this.loanStatus = LoanStatus.REQUESTED;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
