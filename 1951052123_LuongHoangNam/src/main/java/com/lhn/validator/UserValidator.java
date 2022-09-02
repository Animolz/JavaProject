/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.validator;

import com.lhn.pojo.User;
import com.lhn.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Admin
 */
@Component
public class UserValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User)target;
        
        if(u.getUsername().matches("[^\\s-]"))
            errors.rejectValue("username", "user.username.whitespace");
        
        if(u.getPassword().matches("[^\\s-]"))
            errors.rejectValue("username", "user.username.whitespace");
        
    }
    
    
}
