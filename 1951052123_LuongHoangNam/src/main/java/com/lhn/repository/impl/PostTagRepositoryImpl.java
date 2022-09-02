/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;
import com.lhn.repository.PostTagRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class PostTagRepositoryImpl implements PostTagRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addTag(Post post, Tag tag){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            PostTag p1 = new PostTag();
            p1.setPostId(post);
            p1.setTagId(tag);
            session.save(p1);
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
}
