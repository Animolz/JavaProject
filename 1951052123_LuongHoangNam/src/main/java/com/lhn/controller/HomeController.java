/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import com.lhn.service.PostService;
import com.lhn.service.PostTagService;
import com.lhn.service.TagService;
import com.lhn.service.UserService;
import com.lhn.validator.WebAppValidator;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.velocity.runtime.directive.Foreach;
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
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private Cloudinary cloundinary;
    @Autowired
    private TagService tagService;
    @Autowired
    private PostTagService postTagService;
    
    @GetMapping(path = {"/home"})
    public String home(Model model,
            @RequestParam(required = false) Map<String, String> params,
            HttpSession session){
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("users", this.userService.getUsersRand((User)session.getAttribute("currentUser")));
        model.addAttribute("posts",this.postService.getPosts(null, true, page));
        model.addAttribute("counter", this.postService.countPosts());
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        model.addAttribute("tags", this.tagService.getTags(null));
        
        if(!params.isEmpty()){
            if(params.containsKey("tag") && params.containsKey("postId")){
                if(this.postTagService.addTag(Integer.parseInt(params.get("postId")), Integer.parseInt(params.get("tag"))))
                    return "redirect:/home";
                else return "index"; 
            }
        }

        return "index";
    }
    
    @GetMapping(path = {"/home/upstory"})
    public String postInput(Model model){
        model.addAttribute("post", new Post());
        return "upload-post";
    }
    
    @GetMapping(path = {"/home/upauction"})
    public String addAuction(Model model){
        model.addAttribute("post", new Post());
        return "upload-post-auction";
    }
    
    @PostMapping(path = {"/home/upstory/upload"})
    public String addPost(Model model,
            @ModelAttribute(value = "post") @Valid Post post,
            HttpSession session){
        
        if(post.getFile() != null || !post.getFile().isEmpty()){
            try {
                Map r = this.cloundinary.uploader().upload(post.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(r.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        User user = (User)session.getAttribute("currentUser");
        post.setUserId(user);

        if(this.postService.addPost(post))
            return "redirect:/home";
        else{
            model.addAttribute("error", "Error when try to post your Story!");
            return "index";
        }
    }
    
    @PostMapping(path = {"/home/upauction/upload"})
    public String addAuctionPost(Model model,
            @ModelAttribute(value = "post") @Valid Post post,
            HttpSession session){
            
        if(post.getFile() != null || !post.getFile().isEmpty()){
            try {
                Map r = this.cloundinary.uploader().upload(post.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(r.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        User user = (User)session.getAttribute("currentUser");
        post.setUserId(user);
        post.setPrice(Long.parseLong("0"));

        if(this.postService.addAuctionPost(post))
            return "redirect:/home";
        else{
            model.addAttribute("error", "Error when try to post your Story!");
            return "index";
        }
    }
    
    @GetMapping(path = {"/del-post"})
    public String delPost(Model model,
            @RequestParam(value = "postId", required = false) int postId){
        if(postId != 0){
            if(this.postService.disablePost(postId))
                return "redirect:/home";
            else return "index";
        }
        return "index";
    }
    
    @GetMapping(path = {"/report"})
    public String reportUser(Model model,
            @RequestParam(value = "userId") int userId){
        User user = this.userService.getUserById(userId);
        if(this.userService.updateReported(user))
            return "redirect:/home";
        else {
            model.addAttribute("error", "Ther's an error when you tried to report user");
            return "forward:/home";
        }
    }
    
    @GetMapping(path = {"/update-post/{postId}"})
    public String updatePost(Model model,
            @PathVariable(value = "postId") int postId){
        if(postId != 0){
            Post post = this.postService.getPostById(postId, true);
            model.addAttribute("post", post);
            try{
            if(post.getId() != 0){
                if(!post.getIsAuction()){
                    return "upload-post";
                }
                if(post.getIsAuction()){
                    return "upload-post-auction";
                }
            }
            }catch(NullPointerException ex){
                ex.printStackTrace();
            }
        }
        return "index";
    }
    
    @PostMapping(path = {"/update-post/{postId}/update"})
    public String updatePostDirect(Model model,
            @ModelAttribute(value = "post") @Valid Post post,
            @PathVariable(value = "postId") int postId,
            HttpSession session){
            
        if(post.getFile() != null || !post.getFile().isEmpty()){
            try {
                Map r = this.cloundinary.uploader().upload(post.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(r.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(this.postService.updatePost(post, postId))
            return "redirect:/home";
        else{
            model.addAttribute("error", "Ther's an error when we tried to update your story");
            return "forward:/home";
        }
    }
    
    
    
    @GetMapping(path = {"/profile/{username}"})
    public String profile(Model model,
            @PathVariable(value = "username") String username,
            HttpSession session){
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        User user = this.userService.getUsers(param).get(0);
        model.addAttribute("user", user);
        model.addAttribute("posts",this.postService.getPostsByUserId(user, true));
        model.addAttribute("users", this.userService.getUsersRand((User)session.getAttribute("currentUser")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "profile";
    }
    
}
