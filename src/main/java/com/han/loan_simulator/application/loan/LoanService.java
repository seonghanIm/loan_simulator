package com.han.loan_simulator.application.loan;

import com.han.loan_simulator.application.loanProduct.LoanProductService;
import com.han.loan_simulator.application.user.UserService;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.common.baseDto.BaseResponse;
import com.han.loan_simulator.domain.loan.Loan;
import com.han.loan_simulator.domain.loan.LoanMapper;
import com.han.loan_simulator.domain.loan.LoanRepository;
import com.han.loan_simulator.web.loan.LoanRequest;
import com.han.loan_simulator.web.loan.LoanResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final UserService userService;
    private final LoanProductService loanProductService;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Transactional
    public BaseResponse<LoanResponse> register(LoanRequest loanRequest){

        if(!userService.isExistUser(loanRequest.getNickName())){
            return new BaseResponse<LoanResponse>().ofError(RESPONSE.NO_EXIST_USER.code, RESPONSE.NO_EXIST_USER.mesage);
        }

        if(!loanProductService.getLoanProduct(loanRequest.getLoanProductId()).isPresent()){
            return new BaseResponse<LoanResponse>().ofError(RESPONSE.NO_LOAN_PRODUCT_ERROR.code , RESPONSE.NO_LOAN_PRODUCT_ERROR.mesage);
        }

        Loan loan = loanMapper.toEntity(loanRequest);

        loanRepository.save(loan);

        return new BaseResponse<>().ofSuccess()


    }


}
