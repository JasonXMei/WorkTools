package com.jason.enums;

import cn.hutool.http.HttpStatus;

/**
 * @Author Jason
 */
public enum ResponseCodeEnum {

    /**
     * Success
     */
    OK(200, "Success"),

    /**
     * Bad Request
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * Unauthorized
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * Business Exception
     */
    BUSINESS_EXCEPTION(403, "Business Exception"),

    /**
     * Method not support
     */
    HTTP_BAD_METHOD(HttpStatus.HTTP_BAD_METHOD, "Method Not allowed"),

    /**
     * Internal Server Error
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
