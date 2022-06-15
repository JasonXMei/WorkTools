package com.jason.dto;

import com.jason.enums.ResponseCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class RespDTO<T> {

    private int code;
    private String msg;
    private T body;

    public static <T> RespDTO<T> success(T body) {
        return RespDTO.<T>builder()
                .code(ResponseCodeEnum.OK.getCode())
                .msg(ResponseCodeEnum.OK.getMessage())
                .body(body)
                .build();
    }

    public static <T> RespDTO<T> fail(ResponseCodeEnum responseCode, String extraMsg) {
        if (Objects.isNull(extraMsg)) {
            return failWithNoExtraMsg(responseCode);
        } else {
            return RespDTO.<T>builder()
                    .code(responseCode.getCode())
                    .msg(String.format("%s | %s", responseCode.getMessage(), extraMsg))
                    .build();
        }
    }

    private static <T> RespDTO<T> failWithNoExtraMsg(ResponseCodeEnum responseCode) {
        return RespDTO.<T>builder()
                .code(responseCode.getCode())
                .msg(responseCode.getMessage())
                .build();
    }
}
