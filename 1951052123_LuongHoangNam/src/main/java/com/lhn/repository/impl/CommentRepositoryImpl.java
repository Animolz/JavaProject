/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.Comment;
import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import com.lhn.repository.CommentRepository;
import com.lhn.repository.PostRepository;
import com.lhn.repository.UserRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public Comment addComment(Comment c) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(c);
            return c;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getCommentByPostId(int postId){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root root = q.from(Comment.class);
        q.select(root);
        
        q.where(b.equal(root.get("postId"), postId));
        
        q.orderBy(b.desc(root.get("createdDate").as(Date.class)));
        
        return session.createQuery(q).getResultList();
    }
}
