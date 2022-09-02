/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.lhn.pojo.Comment;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;
import com.lhn.service.CommentService;
import com.lhn.service.TagService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;

    @PostMapping(path = {"/load-comments"})
    public ResponseEntity<List<Comment>> loadComment(@RequestBody Map<String, String> params) {

        List<Comment> c = this.commentService.getCommentByPostId(Integer.parseInt(params.get("postId")));
        if (c.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
    @PostMapping(path = {"/add-comment"})
    public ResponseEntity<List<Comment>> addComment(@RequestBody Map<String, String> params){
        Comment c = this.commentService.addComment(params.get("comment"), 
                Integer.parseInt(params.get("postId")), 
                Integer.parseInt(params.get("userId")));
        List<Comment> lc = new ArrayList<>();
        lc.add(c);
        if(lc.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lc, HttpStatus.CREATED);
    }
    
    @PostMapping(path = {"/load-tag"})
    public ResponseEntity<List<Object>> loadTag(@RequestBody Map<String, String> params){
        List<Object> lc = this.tagService.getTagsByPostId(Integer.parseInt(params.get("postId")));
        if(lc.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lc, HttpStatus.CREATED);
    }
    
}
