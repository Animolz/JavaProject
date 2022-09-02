/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository;

import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;

/**
 *
 * @author Admin
 */
public interface PostTagRepository {
    boolean addTag(Post post, Tag tag);
}
