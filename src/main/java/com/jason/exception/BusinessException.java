package com.jason.exception;

import com.jason.enums.ResponseCodeEnum;

/**
 * BusinessException
 *
 * @Author Jason
 * @Date 2022/06/21
 */
public class BusinessException extends RuntimeException {

    private ResponseCodeEnum responseCode;

    public BusinessException(ResponseCodeEnum responseCode, String extraMessage) {
        super(extraMessage);
        this.responseCode = responseCode;
    }

    public ResponseCodeEnum getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodeEnum responseCode) {
        this.responseCode = responseCode;
    }
}
