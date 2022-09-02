/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service.impl;

import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import com.lhn.repository.PostRepository;
import com.lhn.service.PostService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    
    @Override
    public List<Object[]> getPosts(Map<String, String> param) {
        return this.postRepository.getPosts(param);
    }
    
    @Override
    public List<Object[]> getPosts(Map<String, String> map, boolean active, int page) {
        return this.postRepository.getPosts(map, active, page);
    }
    
    @Override
    public Post getPostById(int id, boolean active){
        return this.postRepository.getPostById(id, active);
    }

    @Override
    public boolean disablePost(int postId) {
        return this.postRepository.disablePost(postId);
    }

    @Override
    public boolean addPost(Post post) {
        post.setActive(Boolean.TRUE);
        post.setPostedDate(new Date());
        return this.postRepository.addPost(post);
    }
    
    @Override
    public boolean addAuctionPost(Post post) {
        post.setActive(Boolean.TRUE);
        post.setPostedDate(new Date());
        return this.postRepository.addPost(post);
    }

    @Override
    public List<Object[]> getPostsByUserId(User user, boolean active) {
        return this.postRepository.getPostsByUserId(user, active);
    }

    @Override
    public long countPosts() {
        return this.postRepository.countPosts();
    }

    @Override
    public boolean updatePost(Post post, int postId) {
        return this.postRepository.updatePost(post, postId);
    }
}
