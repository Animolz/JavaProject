/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service.impl;

import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;
import com.lhn.repository.PostRepository;
import com.lhn.repository.PostTagRepository;
import com.lhn.repository.TagRepository;
import com.lhn.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostTagServiceImpl implements PostTagService{
    @Autowired
    private PostTagRepository postTagRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public boolean addTag(int postId, int tagId) {
        Post post = this.postRepository.getPostById(postId, true);
        Tag tag = this.tagRepository.findById(tagId);
        return this.postTagRepository.addTag(post, tag);
    }
    
}
