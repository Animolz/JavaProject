/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.handler;

import com.lhn.pojo.User;
import com.lhn.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class LoginHandler implements AuthenticationSuccessHandler{
    @Autowired
    private UserService userDetailsService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, String> param = new HashMap<>();
        param.put("username", authentication.getName());
        User user = this.userDetailsService.getUsers(param).get(0);
        request.getSession().setAttribute("currentUser", user);
        
        response.sendRedirect("/BTL_clone/home");
    }
    
}
