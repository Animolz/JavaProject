/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.formatter;

import com.lhn.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class UserFormatter implements Formatter<User>{

    @Override
    public String print(User object, Locale locale) {
        return String.valueOf(object.getUsername());
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        User user = new User();
        user.setId(Integer.parseInt(text));
        
        return user;
    }
    
}
