/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lhn.pojo.Tag;
import com.lhn.pojo.User;
import com.lhn.service.TagService;
import com.lhn.service.UserService;
import com.lhn.validator.WebAppValidator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class AdminUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WebAppValidator userValidator;
    
    @InitBinder
    public void initBinding(WebDataBinder binder){
        binder.setValidator(userValidator);
    }

        
    @GetMapping(path = {"/admin/admin-user"})
    public String adminUser(Model model, 
            @RequestParam(value = "kw", required = false) String kw){
        if(kw != null && !kw.isEmpty()){
            Map<String, String> param = new HashMap<>();
            kw = kw.trim().toLowerCase();
            param.put("kw", kw);
            param.put("phone", kw);
            param.put("username", kw);
            param.put("email", kw);
            model.addAttribute("users", this.userService.getUsers(param));
        }
        else model.addAttribute("users", this.userService.getUsers(null));
        
        return "admin-user";
    }
    
    @GetMapping(path = {"/admin/admin-user/{userId}"})
    public String delete(@PathVariable(name = "userId") String userId){
        Map<String, String> param = new HashMap<>();
        param.put("id", userId);
        User user = this.userService.getUsers(param).get(0);
        if(this.userService.updateActive(user))
            return "redirect:/admin/admin-user";
        else return "admin-user";
    }
    
    @GetMapping(path = {"/admin/admin-user/input"})
    public String inputUser(Model model){
        model.addAttribute("user", new User());
        return "admin-user-input";
    }
    
    @PostMapping(path = {"/admin/admin-user/input/add"})
    public String addUser(Model model,
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult result){
        if(!result.hasErrors()){
            if(this.userService.addUser(user))
                return "redirect:/admin/admin-user";
            else{
                model.addAttribute("error", "There's an error when we tried to record your info!!!");
                return "admin-user-input";
            }
        }
        return "admin-user-input";
    }
    
    @PostMapping(path = {"/admin/admin-user/input/{userId}/update"})
    public String updateUser(Model model,
            @ModelAttribute(value = "user") @Valid User user,
            @PathVariable(value = "userId") int id,
            BindingResult result){
        if(!result.hasErrors()){
            if(this.userService.updateUser(user, id))
                return "redirect:/admin/admin-user";
            else{
                model.addAttribute("error", "There's an error when we tried to record your info!!!");
                return "admin-user-input1";
            }
        }
        return "admin-user-input1";
    }
    
    @GetMapping(path = {"/admin/admin-user/input/{userId}"})
    public String inputUser1(Model model,
            @PathVariable(value = "userId") String userId){
        if(userId != null && !userId.isEmpty()){
            Map<String, String> param = new HashMap<>();
            param.put("id", userId);
            User user = this.userService.getUsers(param).get(0);
            model.addAttribute("user", user);
        }
        return "admin-user-input1";
    }
    
    @GetMapping(path = "/admin/admin-user/ban/{userId}")
    public String banUser(Model model,
            @PathVariable(value = "userId") String userId){
        if(userId != null && !userId.isEmpty()){
            Map<String, String> param = new HashMap<>();
            param.put("id", userId);
            User user = this.userService.getUsers(param).get(0);
            if(user.getIsBanned() == false){
                if(this.userService.banUser(user))
                    return "redirect:/admin/admin-user";
                else{
                    model.addAttribute("error", "There's an error when we tried to ban a user!!!");
                    return "admin-user";
                }
            }else {
                 if(this.userService.unbanUser(user))
                    return "redirect:/admin/admin-user";
                else{
                model.addAttribute("error", "There's an error when we tried to ban a user!!!");
                return "admin-user";
                }
            }
        }
        return null;
    }
}
