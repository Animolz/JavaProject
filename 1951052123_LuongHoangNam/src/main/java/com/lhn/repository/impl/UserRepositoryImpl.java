/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.User;
import com.lhn.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<User> getUsers(Map<String, String> param) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);
        
        if(param != null){
            List<Predicate> predicates = new ArrayList<>();
            if (param.containsKey("id")) {
                predicates.add(b.equal(root.get("id").as(Integer.class), Integer.parseInt(param.get("id"))));
            }
            
            if (param.containsKey("kw")) {
                predicates.add(b.like(root.get("fullname").as(String.class),String.format("%%%s%%", param.get("kw"))));
            }
            
            if (param.containsKey("username")) {
                predicates.add(b.equal(root.get("username").as(String.class),String.format("%s", param.get("username"))));
            }
            
            if (param.containsKey("phone")) {
                predicates.add(b.like(root.get("phone").as(String.class),String.format("%%%s%%", param.get("phone"))));
            }
            Predicate finalCriteria = b.or(predicates.toArray(new Predicate[predicates.size()]));
            q = q.where(finalCriteria);
        }
        
        q = q.orderBy(b.asc(root.get("id")));

        Query query = session.createQuery(q);

        return query.getResultList();
    }
    
    @Override
    public List<User> getUsersRand(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);
        
        q.where(b.notEqual(root.get("username").as(String.class), user.getUsername()));
        
        q.orderBy(b.asc(b.function("RAND", null)));

        Query query = session.createQuery(q);
        
        query.setMaxResults(3);

        return query.getResultList();
    }

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            user.setCreatedTime(new Date());
            user.setActive(true);
            user.setUserRole(user.USER);
            session.save(user);
            return true;
        }
        catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(User.class);
        Root root = u.from(User.class);
        u.set("fullname", user.getFullname());
        u.set("username", user.getUsername());
        u.set("password", user.getPassword());
        u.set("email", user.getEmail());
        u.set("phone", user.getPhone());
        u.set("userRole", user.getUserRole());
        u.where(b.equal(root.get("id").as(Integer.class), id));
        
        return session.createQuery(u).executeUpdate() > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaDelete<User> u = b.createCriteriaDelete(User.class);
        Root root = u.from(User.class);
        u.where(b.equal(root.get("id").as(Integer.class), id));
        
        return session.createQuery(u).executeUpdate()> 0;
    }

    @Override
    public boolean updateActive(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(User.class);
        Root root = u.from(User.class);
        u.set("active", false);
        u.where(b.equal(root.get("id").as(Integer.class), user.getId()));
        
        return session.createQuery(u).executeUpdate() > 0;
    }
    
    @Override
    public boolean updateReported(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(User.class);
        Root root = u.from(User.class);
        u.set("isReported", true);
        u.where(b.equal(root.get("id").as(Integer.class), user.getId()));
        
        return session.createQuery(u).executeUpdate() > 0;
    }

    @Override
    public boolean banUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(User.class);
        Root root = u.from(User.class);
        u.set("isBanned", true);
        u.set("active", false);
        u.where(b.equal(root.get("id").as(Integer.class), user.getId()));
        
        return session.createQuery(u).executeUpdate() > 0;
    }
    
    @Override
    public boolean unbanUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(User.class);
        Root root = u.from(User.class);
        u.set("isBanned", false);
        u.set("active", true);
        u.where(b.equal(root.get("id").as(Integer.class), user.getId()));
        
        return session.createQuery(u).executeUpdate() > 0;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        
        Root rootP = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootP.get("id").as(Integer.class), id));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        return session.createQuery(q).getSingleResult();
    }

    @Override
    public User getUserByUsername(String string) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        
        Root rootP = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootP.get("username").as(String.class), string));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        return session.createQuery(q).getSingleResult();
    }
}
