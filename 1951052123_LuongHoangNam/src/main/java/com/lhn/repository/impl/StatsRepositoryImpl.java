/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.Like1;
import com.lhn.pojo.Post;
import com.lhn.pojo.User;
import com.lhn.repository.StatsRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> countPosts() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Post.class);
        Root rootU = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rootP.get("userId"), rootU.get("id")));
        predicates.add(b.equal(rootP.get("isAuction").as(boolean.class), false));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        q.multiselect(rootU.get("username"), b.count(rootP.get("id")));
        
        q.groupBy(rootP.get("userId"));
        
        return s.createQuery(q).getResultList();
    }

    @Override
    public List<Object[]> countAuctionPosts() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Post.class);
        Root rootU = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rootP.get("userId"), rootU.get("id")));
        predicates.add(b.equal(rootP.get("isAuction").as(boolean.class), true));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        q.multiselect(rootU.get("username"), b.count(rootP.get("id")));
        
        q.groupBy(rootP.get("userId"));
        
        return s.createQuery(q).getResultList();
    }
    
    @Override
    public List<Object[]> countPosts(Date startDate, Date endDate) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Post.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootP.get("isAuction").as(boolean.class), false));
        predicates.add(b.between(rootP.get("createdDate").as(Date.class), startDate, endDate));
        
        q.where(predicates.toArray(new Predicate[]{}));    
        
        q.multiselect(b.count(rootP.get("id")));
        
        return s.createQuery(q).getResultList();
    }
    
    @Override
    public List<Object[]> countAuctionPosts(Date startDate, Date endDate) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Post.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootP.get("isAuction").as(boolean.class), true));
        predicates.add(b.between(rootP.get("createdDate").as(Date.class), startDate, endDate));
        
        q.where(predicates.toArray(new Predicate[]{}));    
        
        q.multiselect(b.count(rootP.get("id")));
        
        return s.createQuery(q).getResultList();
    }
}
