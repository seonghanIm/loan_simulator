package com.han.loan_simulator.domain.loan;

import com.han.loan_simulator.application.loanProduct.LoanProductService;
import com.han.loan_simulator.application.user.UserService;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.user.User;
import com.han.loan_simulator.web.loan.LoanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoanMapper {
    private final UserService userService;
    private final LoanProductService loanProductService;

    public Loan toEntity(LoanRequest loanRequest) {
        Optional<User> user = userService.getUserBy(loanRequest.getNickName());
        Optional<LoanProduct> loanProduct = loanProductService.getLoanProduct(loanRequest.getLoanProductId());
        return Loan.builder()
                .user(user.orElse(null))
                .loanProduct(loanProduct.orElse(null))
                .interestRate(loanProduct.get().getInterestRate())
                .termMonths(loanRequest.getTermMonths())
                .principal(loanRequest.getPrincipal())
                .startDate(loanRequest.getStartDate())
                .endDate(loanRequest.getStartDate().plusMonths(loanRequest.getTermMonths()))
                .build();
    }

}
