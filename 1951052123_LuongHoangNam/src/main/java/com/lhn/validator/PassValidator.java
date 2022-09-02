/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.validator;

import com.lhn.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Admin
 */
@Component
public class PassValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target; 
        if (!u.getPassword().trim().equals(u.getConfirmPassword().trim()))
        errors.rejectValue("password", "user.password.error.notMatchMsg");
    } 
}
