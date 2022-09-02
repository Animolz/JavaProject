/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service.impl;

import com.lhn.pojo.Comment;
import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import com.lhn.repository.CommentRepository;
import com.lhn.repository.PostRepository;
import com.lhn.repository.UserRepository;
import com.lhn.service.CommentService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    
    
    @Override
    public List<Comment> getCommentByPostId(int postId){
        return this.commentRepository.getCommentByPostId(postId);
    }
    
    @Override
    public Comment addComment(String content, int postId, int userId) {
        Post p = this.postRepository.getPostById(postId, true);
        User u = this.userRepository.getUserById(userId);
        
        Comment c = new Comment();
        c.setComment(content);
        c.setPostId(p);
        c.setUserId(u);
        
        return this.commentRepository.addComment(c);
    }
    
}
