package com.jason.handler;

import com.jason.dto.RespDTO;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RespDTO<String> handleException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RespDTO.fail(e.getResponseCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespDTO<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RespDTO.fail(ResponseCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
