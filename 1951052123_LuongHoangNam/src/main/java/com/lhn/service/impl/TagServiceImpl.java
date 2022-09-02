/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service.impl;

import com.lhn.pojo.Post;
import com.lhn.pojo.Tag;
import com.lhn.repository.PostRepository;
import com.lhn.repository.TagRepository;
import com.lhn.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Tag> getTags(String tag) {
        return this.tagRepository.getTags(tag);
    }

    @Override
    public boolean addTag(Tag tag) {
        return this.tagRepository.addTag(tag);
    }

    @Override
    public boolean updateTag(Tag tag, int id) {
        return this.tagRepository.updateTag(tag, id);
    }

    @Override
    public boolean deleteTag(int id) {
        return this.tagRepository.deleteTag(id);
    }

    @Override
    public Tag findById(int i) {
        return this.tagRepository.findById(i);
    }

    @Override
    public List<Object> getTagsByPostId(int postId) {
        Post post = this.postRepository.getPostById(postId, true);
        return this.tagRepository.getTagsByPostId(post);
    }
    
    
}
