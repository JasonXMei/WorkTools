package com.jason.handler;

import com.jason.dto.RespDTO;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RespDTO<String> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RespDTO.fail(e.getResponseCode(), e.getMessage());
    }

    // @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseBody
    public RespDTO<String> handleParameterVerificationException(Exception e) {
        log.error("handleParameterVerificationException has been invoked", e);
        return RespDTO.fail(ResponseCodeEnum.BAD_REQUEST, getBadParemeterMsg(e));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RespDTO<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException has been invoked", e);
        return RespDTO.fail(ResponseCodeEnum.HTTP_BAD_METHOD, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespDTO<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RespDTO.fail(ResponseCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private String getBadParemeterMsg(Exception ex) {
        StringJoiner addOnMessage = new StringJoiner(" | ");
        if (ex instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException)ex).getConstraintViolations();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                String field = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                String errorMsg = String.format("%s:%s", field, message);
                addOnMessage.add(errorMsg);
            }
        } else {
            BindingResult bindingResult = null;
            if (ex instanceof MethodArgumentNotValidException) {
                bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            } else if (ex instanceof BindException) {
                bindingResult = ((BindException) ex).getBindingResult();
            } else {
            }

            List<ObjectError> errors = bindingResult.getAllErrors();
            if (errors != null) {
                for (ObjectError error : errors) {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        String field = fieldError.getField();
                        String message = fieldError.getDefaultMessage();
                        String errorMsg = String.format("%s:%s", field, message);
                        addOnMessage.add(errorMsg);
                    }
                }
            }

        }
        return addOnMessage.toString();
    }

}
