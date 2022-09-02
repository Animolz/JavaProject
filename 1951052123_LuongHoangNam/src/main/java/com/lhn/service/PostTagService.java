/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service;

import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;

/**
 *
 * @author Admin
 */
public interface PostTagService {
    boolean addTag(int post, int tag);
}
