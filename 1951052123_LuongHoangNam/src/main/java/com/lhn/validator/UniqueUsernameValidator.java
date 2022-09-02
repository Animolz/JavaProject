/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.validator;

import com.lhn.annotation.UserExist;
import com.lhn.service.UserService;
import javax.persistence.NoResultException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public class UniqueUsernameValidator implements ConstraintValidator<UserExist, String>{
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            return value != null && !this.userService.getUserByUsername(value);
        }
        catch(NoResultException ex){
            return false;
        }
    }

    @Override
    public void initialize(UserExist constraintAnnotation) {
    }
    
}
