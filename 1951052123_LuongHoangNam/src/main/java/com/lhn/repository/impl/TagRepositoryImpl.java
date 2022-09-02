/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;
import com.lhn.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class TagRepositoryImpl implements TagRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Tag> getTags(String tag) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Tag.class);
        Root root = q.from(Tag.class);
        q.select(root);
        
        if(tag != null && !tag.isEmpty()){
            Predicate p1 = b.like(root.get("tag").as(String.class), String.format("%%%s%%", tag));
            q = q.where(p1);
        }
        q = q.orderBy(b.asc(root.get("id")));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public boolean addTag(Tag tag) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(tag);
            return true;
        }
        catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTag(Tag tag, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(Tag.class);
        Root root = u.from(Tag.class);
        u.set("tag", tag.getTag());
        u.where(b.equal(root.get("id").as(Integer.class), id));
        
        return session.createQuery(u).executeUpdate() > 0;
    }

    @Override
    public boolean deleteTag(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaDelete<Tag> u = b.createCriteriaDelete(Tag.class);
        Root root = u.from(Tag.class);
        u.where(b.equal(root.get("id").as(Integer.class), id));
        
        return session.createQuery(u).executeUpdate()> 0;
    }

    @Override
    public Tag findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Tag.class);
        Root root = q.from(Tag.class);
        q.select(root);
        
        Predicate p1 = b.equal(root.get("id").as(Integer.class), id);
        q = q.where(p1);
        
        Query query = session.createQuery(q);
        
        return (Tag)query.getResultList().get(0);
    }    

    @Override
    public List<Object> getTagsByPostId(Post postId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Tag.class);
        Root rootT = q.from(Tag.class);
        Root rootPT = q.from(PostTag.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootT.get("id"), rootPT.get("tagId")));
        predicates.add(b.equal(rootPT.get("postId"), postId));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        q.select(rootT);
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

}
