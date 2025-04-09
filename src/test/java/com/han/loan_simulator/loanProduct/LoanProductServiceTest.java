package com.han.loan_simulator.loanProduct;

import com.han.loan_simulator.common.baseDto.BaseResponse;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.application.loanProduct.LoanProductService;
import com.han.loan_simulator.domain.loanProduct.LoanProduct;
import com.han.loan_simulator.domain.loanProduct.LoanProductRepository;
import com.han.loan_simulator.domain.loanProduct.RepaymentMethod;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductRequest;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

        BaseResponse<LoanProductResponse> response =  loanProductService.register(request);

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

        BaseResponse<LoanProductResponse> response = loanProductService.register(request);
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.INPUT_TERM_ERROR.code);
    }

    @Test
    void findById_정상조회(){
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setId(1L);
        loanProduct.setName("test");

        when(loanProductRepository.findById(1L)).thenReturn(Optional.of(loanProduct));

        BaseResponse<LoanProductResponse> response = loanProductService.findById(1L);

        assertThat(response.getCode()).isEqualTo(RESPONSE.SUCCESS.code);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().loanProductId).isEqualTo(1L);
        assertThat(response.getData().name).isEqualTo("test");
    }

    @Test
    void findById_없는상품(){
        Long id = 1L;
        when(loanProductRepository.findById(id)).thenReturn(Optional.empty());

        BaseResponse<LoanProductResponse> response = loanProductService.findById(id);

        assertThat(response.getCode()).isEqualTo(RESPONSE.NO_LOAN_PRODUCT_ERROR.code);
        assertThat(response.getData()).isNull();
    }

    @Test
    void findAll_정상조회(){
        Pageable pageable = PageRequest.of(0,10);
        LoanProduct product = new LoanProduct();
        product.setId(1L);
        product.setName("상품1");

        Page<LoanProduct> page = new PageImpl<>(List.of(product));

        when(loanProductRepository.findAll(pageable)).thenReturn(page);

        BaseResponse<Page<LoanProductResponse>> response = loanProductService.findAll(pageable);
        assertThat(response.getCode()).isEqualTo(RESPONSE.SUCCESS.code);
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getContent()).hasSize(1);
        assertThat(response.getData().getContent().get(0).getName()).isEqualTo("상품1");
    }

    @Test
    void findAll_상품이없는경우(){
        Pageable pageable = PageRequest.of(0,10);
        when(loanProductRepository.findAll(pageable)).thenReturn(Page.empty());

        BaseResponse<Page<LoanProductResponse>> response = loanProductService.findAll(pageable);

        assertThat(response.getCode()).isEqualTo(RESPONSE.NO_LOAN_PRODUCT_ERROR.code);
        assertThat(response.getData()).isNull();
    }


}
