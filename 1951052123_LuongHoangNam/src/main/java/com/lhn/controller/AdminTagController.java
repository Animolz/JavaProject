/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.lhn.pojo.Tag;
import com.lhn.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class AdminTagController {
    @Autowired
    private TagService tagService;
        
    @GetMapping(path = {"/admin/admin-tag"})
    public String adminTag(Model model,
                @RequestParam(value = "kw", required = false) String kw){
        if(kw != null && !kw.isEmpty()){
            kw = kw.trim().toLowerCase();
            model.addAttribute("tags", this.tagService.getTags(kw));
        }
        else
            model.addAttribute("tags", this.tagService.getTags(null));
        return "admin-tag";
    }
    
    @GetMapping(path = {"/admin/admin-tag/input"})
    public String adminTagInput(Model model){
        
        model.addAttribute("tag", new Tag());
        return "admin-tag-input";
    }
    
    @PostMapping(path = {"/admin/admin-tag/input/add"})
    public String addTag(Model model,
                @ModelAttribute(value = "tag") Tag tag){
    
        if(this.tagService.addTag(tag))
            return "redirect:/admin/admin-tag";
        else{
            model.addAttribute("error", "There's an error when we tried to record your tag!!!");
            return "admin-tag-input";
        }
    }
    
    @GetMapping(path = {"/admin/admin-tag/{tagId}"})
    public String deleteTag(Model model,
                @PathVariable(value = "tagId") int id){
        if(this.tagService.deleteTag(id))
            return "redirect:/admin/admin-tag";
        else{
            model.addAttribute("error", "There's an error when we tried to delete your tag!!!");
            return "admin-tag";
        }
    }
    
    @GetMapping(path = {"/admin/admin-tag/input/{tagId}"})
    public String InputUpdateTag(Model model,
                @PathVariable(value = "tagId") int id){
        Tag tag = this.tagService.findById(id);
        model.addAttribute("tag", tag);
        
        return "admin-tag-input1";
    }
    
    @PostMapping(path = {"/admin/admin-tag/input/{tagId}/update"})
    public String updateTag(Model model,
                @ModelAttribute(value = "tag") Tag tag,
                @PathVariable(value = "tagId") int tagId){
        if(this.tagService.updateTag(tag, tagId))
            return "redirect:/admin/admin-tag";
        else{
            model.addAttribute("error", "There's an error when we tried to update your tag!!!");
            return "admin-tag-input1";
        }
    }
}
