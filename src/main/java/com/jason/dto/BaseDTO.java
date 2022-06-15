package com.jason.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class BaseDTO {

    private int code;
    private String msg;

    public boolean isOk() {
        return Objects.equals(code, 200);
    }

}
