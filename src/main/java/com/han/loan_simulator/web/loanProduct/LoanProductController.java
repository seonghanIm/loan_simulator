package com.han.loan_simulator.web.loanProduct;

import com.han.loan_simulator.application.loanProduct.LoanProductService;
import com.han.loan_simulator.common.base.BaseResponse;
import com.han.loan_simulator.web.loanProduct.dto.LoanProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class LoanProductController {

    private final LoanProductService loanProductService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody LoanProductRequest request){
        return loanProductService.register(request);
    }
}
