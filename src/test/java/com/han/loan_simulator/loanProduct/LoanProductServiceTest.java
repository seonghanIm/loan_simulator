package com.han.loan_simulator.loanProduct;

import com.han.loan_simulator.common.BaseResponse;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.domain.application.loanProduct.LoanProductService;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductRequest;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LoanProductServiceTest {
    private LoanProductRepository loanProductRepository;
    private LoanProductService loanProductService;

    @BeforeEach
    void setUp(){
        loanProductRepository = mock(LoanProductRepository.class);
        loanProductService = new LoanProductService(loanProductRepository);
    }


    @Test
    void 대출상품_정상등록(){
        LoanProductRequest request = LoanProductRequest.builder()
                .name("test 대출")
                .minAmount(new BigDecimal("100000000"))
                .maxAmount(new BigDecimal("200000000"))
                .minTermMonth(6)
                .maxTermMonth(36)
                .interestRate(new BigDecimal("8"))
                .repaymentMethod(RepaymentMethod.EQUAL_PRINCIPAL)
                .build();

        LoanProduct loanProduct = request.toEntity();
        when(loanProductRepository.save(any(LoanProduct.class))).thenReturn(loanProduct);

        BaseResponse response = loanProductService.register(request);

        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void 대출상품_금액_오류(){
        LoanProductRequest request = LoanProductRequest.builder()
                .name("test 대출")
                .minAmount(new BigDecimal("3"))
                .maxAmount(new BigDecimal("2"))
                .minTermMonth(6)
                .maxTermMonth(36)
                .interestRate(new BigDecimal("8"))
                .repaymentMethod(RepaymentMethod.EQUAL_PRINCIPAL)
                .build();

        BaseResponse response =  loanProductService.register(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.INPUT_AMOUNT_ERROR.code);

    }

    @Test
    void 대출상품_기간_오류(){
        LoanProductRequest request = LoanProductRequest.builder()
                .name("test 대출")
                .minAmount(new BigDecimal("1"))
                .maxAmount(new BigDecimal("2"))
                .minTermMonth(30)
                .maxTermMonth(20)
                .interestRate(new BigDecimal("8"))
                .repaymentMethod(RepaymentMethod.EQUAL_PRINCIPAL)
                .build();

        BaseResponse response = loanProductService.register(request);
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.INPUT_TERM_ERROR.code);
    }


}
