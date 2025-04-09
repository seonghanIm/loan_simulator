package com.han.loan_simulator.common;

public enum RESPONSE {
    SUCCESS(200, "성공"),
    ERROR(500,"서버 에러"),
    INPUT_ERROR(501, "입력을 확인해주세요."),
    INPUT_AMOUNT_ERROR(502, "대출 최소 금액이 대출 최대 금액보다 큽니다."),
    INPUT_TERM_ERROR(503, "최소 대출 기간이 최대 대출 기간보다 큽니다."),
    NO_LOAN_PRODUCT_ERROR(504, "대출 상품이 존재하지 않습니다."),
    NICK_NAME_VALIDATE_ERROR(505,"닉네임은 20자 이하의 한글,영문,숫자만 가능합니다."),
    NICK_NAME_DUPLICATED_ERROR(506, "중복되는 닉네임 입니다."),
    NO_EXIST_USER(507, "존재하지 않는 유저 입니다.");


    public int code;
    public String mesage;

    RESPONSE(int code, String message){
        this.code = code;
        this.mesage = message;
    }
}
