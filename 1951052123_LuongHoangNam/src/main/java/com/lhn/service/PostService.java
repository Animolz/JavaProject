/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service;

import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface PostService {
    List<Object[]> getPosts(Map<String, String> param);
    List<Object[]> getPosts(Map<String, String> param, boolean active, int page);
    List<Object[]> getPostsByUserId(User user, boolean active);
    Post getPostById(int id, boolean active);
    boolean addPost(Post post);
    boolean addAuctionPost(Post post);
    boolean disablePost(int postId);
    long countPosts();
    boolean updatePost(Post post, int postId);
}
