package com.han.loan_simulator.application.loanProduct;

import com.han.loan_simulator.common.base.BaseResponse;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductRequest;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanProductService {
    private final LoanProductRepository loanProductRepository;


    @Transactional
    public BaseResponse<LoanProductResponse> register(LoanProductRequest request){
        try {
            LoanProduct loanProductEntity = request.toEntity();

            if (!loanProductEntity.isValidAmount()) {
                return new BaseResponse<LoanProductResponse>()
                        .ofError(RESPONSE.INPUT_AMOUNT_ERROR.code,
                                RESPONSE.INPUT_AMOUNT_ERROR.mesage);
            }

            if (!loanProductEntity.isValidTerm()) {
                return new BaseResponse<LoanProductResponse>()
                        .ofError(RESPONSE.INPUT_TERM_ERROR.code,
                                RESPONSE.INPUT_TERM_ERROR.mesage);
            }

            loanProductRepository.save(loanProductEntity);
            LoanProductResponse response = new LoanProductResponse(loanProductEntity.getId());

            return new BaseResponse<LoanProductResponse>().ofSuccess(response);

        }catch (Exception e){

            log.error("LoanProductService :: register :: error :: {}",e.toString());
            return new BaseResponse<LoanProductResponse>()
                    .ofError(RESPONSE.ERROR.code,
                            RESPONSE.ERROR.mesage);
        }
    }


    @Transactional
    public BaseResponse<LoanProductResponse> findById(Long id){
        try {
            Optional<LoanProduct> product = loanProductRepository.findById(id);

            if (product.isEmpty()) {
                return new BaseResponse<LoanProductResponse>()
                        .ofError(RESPONSE.NO_LOAN_PRODUCT_ERROR.code,
                                RESPONSE.NO_LOAN_PRODUCT_ERROR.mesage);
            }

            LoanProductResponse response = new LoanProductResponse();
            response.fromEntity(product.get());

            return new BaseResponse<>();
        }catch (Exception e){
            log.error("LoanProductService :: findById :: error :: {}",e.toString());
            return new BaseResponse<LoanProductResponse>()
                    .ofError(RESPONSE.ERROR.code,
                            RESPONSE.ERROR.mesage);
        }
    }

    /**
     * todo 페이징 처리
     */
    @Transactional
    public BaseResponse<List<LoanProductResponse>> findAll(){
        try {
            List<LoanProduct> loanProductList = loanProductRepository.findAll();

            if (loanProductList.isEmpty()) {
                return new BaseResponse<List<LoanProductResponse>>()
                        .ofError(RESPONSE.NO_LOAN_PRODUCT_ERROR.code,
                                RESPONSE.NO_LOAN_PRODUCT_ERROR.mesage);
            }

            List<LoanProductResponse> responseList = new ArrayList<>();

            for (LoanProduct product : loanProductList) {
                responseList.add(product.toResponse());
            }

            return new BaseResponse<List<LoanProductResponse>>().ofSuccess(responseList);
        }catch (Exception e){
            log.error("LoanProductService :: findAll :: error :: {}",e.toString());
            return new BaseResponse<List<LoanProductResponse>>()
                    .ofError(RESPONSE.ERROR.code,
                            RESPONSE.ERROR.mesage);
        }

    }


}
