/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.annotation;

import com.lhn.validator.UniqueUsernameValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author Admin
 */
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface UserExist {
    String message() default "User exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
