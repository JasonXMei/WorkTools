package com.jason.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author Jason
 * @Date 2022/12/01
 *
 * 表示一个用户的信息是无冲突的，无冲突是指该用户的敏感信息与其他用户不重合
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = UserValidation.NotConflictUserValidator.class)
public @interface NotConflictUser {
    String message() default "邮箱与现存用户产生重复";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
