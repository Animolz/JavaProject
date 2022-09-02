/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository;

import com.lhn.pojo.Comment;
import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentRepository {
    Comment addComment(Comment c);
    List<Comment> getCommentByPostId(int postId);
}
