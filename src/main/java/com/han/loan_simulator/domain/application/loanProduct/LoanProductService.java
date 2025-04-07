package com.han.loan_simulator.domain.application.loanProduct;

import com.han.loan_simulator.common.BaseResponse;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductRequest;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanProductService {
    private final LoanProductRepository loanProductRepository;


    @Transactional
    public BaseResponse register(LoanProductRequest request){
        LoanProduct loanProductEntity = request.toEntity();

        if(!loanProductEntity.isValidAmount()){
            return new BaseResponse()
                    .ofError(RESPONSE.INPUT_AMOUNT_ERROR.code,
                            RESPONSE.INPUT_AMOUNT_ERROR.mesage);
        }
        if(!loanProductEntity.isValidTerm()){
            return new BaseResponse()
                    .ofError(RESPONSE.INPUT_TERM_ERROR.code,
                            RESPONSE.INPUT_TERM_ERROR.mesage);
        }
        LoanProductResponse response = new LoanProductResponse(loanProductEntity.getId());
        response.ofSuccess();
        return response;
    }


}
