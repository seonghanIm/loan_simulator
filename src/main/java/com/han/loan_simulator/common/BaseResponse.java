package com.han.loan_simulator.common;

import lombok.Getter;

@Getter
public class BaseResponse {
    int code;
    String message;

    public BaseResponse ofError(int code, String message){
        this.code = code;
        this.message = message;
        return this;
    }

    public BaseResponse ofSuccess(){
        this.code = RESPONSE.SUCCESS.code;
        this.message = RESPONSE.SUCCESS.mesage;
        return this;
    }

    public boolean isSuccess(){
        if (this.code == RESPONSE.SUCCESS.code) return true;
        return false;
    }
}
