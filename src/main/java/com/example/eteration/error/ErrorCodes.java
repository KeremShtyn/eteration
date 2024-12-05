package com.example.eteration.error;

import com.example.eteration.util.exception.ErrorCode;
import org.springframework.http.HttpStatus;


public enum ErrorCodes implements ErrorCode {


    SYSTEM_FAILURE(-1, "ErrorCodes.SYSTEM_FAILURE", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCESS_DENIED(101, "ErrorCodes.ACCESS_DENIED", HttpStatus.FORBIDDEN),
    DATA_NOT_FOUND(100, "ErrorCodes.ACCESS_DENIED", HttpStatus.BAD_REQUEST),
    BALANCE_IS_NOT_ENOUGH(100,"ErrorCodes.BALANCE_IS_NOT_ENOUGH" , HttpStatus.BAD_REQUEST),
    AMOUNT_CAN_NOT_BE_LOWER_THAN_ZERO(100,"ErrorCodes.AMOUNT_CAN_NOT_BE_LOWER_THAN_ZERO" , HttpStatus.BAD_REQUEST),
    AMOUNT_CAN_NOT_BE_BIGGER_THAN_TEN_THOUSAND_ON_DEPOSIT(100,"ErrorCodes.AMOUNT_CAN_NOT_BE_BIGGER_THAN_TEN_THOUSAND_ON_DEPOSIT" , HttpStatus.BAD_REQUEST),
    UNKNOWN_TRANSACTION_TYPE(100,"ErrorCodes.UNKNOWN_TRANSACTION_TYPE" , HttpStatus.BAD_GATEWAY),
    PAYEE_OR_TAX_NUMBER_CAN_NOT_BE_EMPTY(100,"ErrorCodes.PAYEE_OR_TAX_NUMBER_CAN_NOT_BE_EMPTY" , HttpStatus.BAD_REQUEST),
    ;

    private ErrorCodes(Integer code, String langKey, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.langKey = langKey;
    }


    private Integer code;

    private String langKey;

    private HttpStatus httpStatus;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String langKey() {
        return this.langKey;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getLangKey() {
        return langKey;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
