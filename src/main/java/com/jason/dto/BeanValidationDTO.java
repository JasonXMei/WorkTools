package com.jason.dto;

import com.jason.validation.DateValidation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Data
public class BeanValidationDTO {

    /** 字符串不能为null,字符串trim()后也不能等于“” */
    @NotBlank
    private String notBlankStr;

    /** 不能为null，可以是空 */
    @NotNull
    private String notNullStr;

    @Length(min = 1, max = 10)
    private String lengthStr;

    @DecimalMax(value = "10.3")
    @DecimalMin(value = "5.1")
    private BigDecimal minAndMaxDecimal;

    @Min(10)
    @Max(15)
    private Long minAndMaxNumber;

    @Email
    private String email;

    @DateValidation(format = "yyyy-MM-dd", message = "date format should be yyyy-MM-dd")
    private String date;

    @DateValidation(format = "yyyy-MM-dd hh:mm:ss", message = "date format should be yyyy-MM-dd hh:mm:ss")
    private String dateTime;

    @Pattern(regexp =  "\\d+")
    private String pattern;

    @Size(min = 0, max = 5)
    private List<Integer> size;
}
