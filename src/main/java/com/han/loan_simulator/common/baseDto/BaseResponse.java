package com.han.loan_simulator.common.baseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.han.loan_simulator.common.RESPONSE;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public BaseResponse<T> ofError(int code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
        return this;
    }

    public BaseResponse<T> ofSuccess(){
        this.code = RESPONSE.SUCCESS.code;
        this.message = RESPONSE.SUCCESS.mesage;
        this.data = null;
        return this;
    }

    public BaseResponse<T> ofSuccess(T data){
        this.code = RESPONSE.SUCCESS.code;
        this.message = RESPONSE.SUCCESS.mesage;
        this.data = data;
        return this;
    }

    @JsonIgnore
    public boolean isSuccess(){
        if (this.code == RESPONSE.SUCCESS.code) return true;
        return false;
    }
}
