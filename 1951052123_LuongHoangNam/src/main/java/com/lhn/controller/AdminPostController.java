/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.lhn.service.PostService;
import com.lhn.service.StatsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class AdminPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private StatsService statsService;
    
    @GetMapping(path = {"/admin/admin-post"})
    public String adminPost(Model model,
            @RequestParam(value = "kw", required = false) String kw){
        if(kw != null && !kw.isEmpty()){
            Map<String, String> param = new HashMap<>();
            kw = kw.trim().toLowerCase();
            param.put("content", kw);
            model.addAttribute("posts", this.postService.getPosts(param));
        }
        else model.addAttribute("posts", this.postService.getPosts(null));
        
        return "admin-post";
    }
    
    @GetMapping(path = {"/admin/admin-post/disable/{postId}"})
    public String disablePost(Model model,
            @PathVariable(value = "postId") int postId){
        if(this.postService.disablePost(postId))
            return "redirect:/admin/admin-post";
        else{
            model.addAttribute("error", "There's an error when we tried to disable your post!!!");
            return "admin-post";
        }
    }

}
