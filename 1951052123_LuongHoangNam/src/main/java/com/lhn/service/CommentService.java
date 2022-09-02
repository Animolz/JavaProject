/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service;

import com.lhn.pojo.Comment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentService {
    Comment addComment(String comment, int postId, int userId);
    List<Comment> getCommentByPostId(int postId);
}
