
package com.lhn.repository.impl;


import com.lhn.pojo.Post;
import com.lhn.pojo.PostTag;
import com.lhn.pojo.Tag;
import com.lhn.pojo.User;
import com.lhn.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class PostRepositoryImpl implements PostRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Object[]> getPosts(Map<String, String> param) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root rootP = q.from(Post.class);
        Root rootU = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rootP.get("userId"), rootU.get("id")));
        
        if(param != null && !param.isEmpty()){
            if(param.containsKey("id"))
                predicates.add(b.equal(rootP.get("id").as(Integer.class), Integer.parseInt(param.get("id"))));
            if(param.containsKey("content"))
                predicates.add(b.like(rootP.get("content").as(String.class), String.format("%%%s%%", param.get("content"))));
            if(param.containsKey("isAuction"))
                predicates.add(b.equal(rootP.get("isAuction").as(Boolean.class), Boolean.parseBoolean(param.get("isAuction"))));
        }
        q = q.where(predicates.toArray(new Predicate[]{}));
        
        q.multiselect(rootP.get("id"),rootP.get("content"),rootP.get("image"),rootP.get("postedDate"),rootP.get("isAuction"),rootP.get("price"),
                        rootP.get("active"), rootU.get("id"),rootU.get("username"), rootU.get("avatar"));
        
        return session.createQuery(q).getResultList();
    }
    
    @Override
    public List<Object[]> getPostsByUserId(User user, boolean active) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root rootP = q.from(Post.class);
        Root rootU = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rootP.get("userId"), rootU.get("id")));
        
        if(user.getUsername() != null && !user.getUsername().isEmpty()){
            predicates.add(b.equal(rootP.get("userId"), user));
        }
        predicates.add(b.equal(rootP.get("active").as(boolean.class), active));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        q.orderBy(b.desc(rootP.get("postedDate")));
        
        q.multiselect(rootP.get("id"),rootP.get("content"),rootP.get("image"),rootP.get("postedDate"),rootP.get("isAuction"),rootP.get("price"),
                        rootP.get("active"), rootU.get("id"),rootU.get("username"), rootU.get("avatar"));
        
        return session.createQuery(q).getResultList();
    }
    
    @Override
    public Post getPostById(int id, boolean active) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Post> q = b.createQuery(Post.class);
        
        Root rootP = q.from(Post.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rootP.get("id").as(Integer.class), id));
        predicates.add(b.equal(rootP.get("active").as(boolean.class), active));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        
        return session.createQuery(q).getResultList().get(0);
    }
    
    @Override
    public List<Object[]> getPosts(Map<String, String> param, boolean active, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root rootP = q.from(Post.class);
        Root rootU = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rootP.get("userId"), rootU.get("id")));
        
        if(param != null && !param.isEmpty()){
            if(param.containsKey("id"))
                predicates.add(b.equal(rootP.get("id").as(Integer.class), Integer.parseInt(param.get("id"))));
            if(param.containsKey("content"))
                predicates.add(b.like(rootP.get("content").as(String.class), String.format("%%%s%%", param.get("content"))));
            if(param.containsKey("isAuction"))
                predicates.add(b.equal(rootP.get("isAuction").as(Boolean.class), Boolean.parseBoolean(param.get("isAuction"))));
        }
        predicates.add(b.equal(rootP.get("active").as(boolean.class), active));
        predicates.add(b.equal(rootU.get("active"), true));
        
        q.where(predicates.toArray(new Predicate[]{}));
        
        q.orderBy(b.desc(rootP.get("postedDate")));
        
        q.multiselect(rootP.get("id"),rootP.get("content"),rootP.get("image"),rootP.get("postedDate"),rootP.get("isAuction"),rootP.get("price"),
                        rootP.get("active"), rootU.get("id"),rootU.get("username"), rootU.get("avatar"));
        
        Query query = session.createQuery(q);
        
        int max = 10;
        query.setMaxResults(max);
        query.setFirstResult((page - 1) * max); 
        
        return query.getResultList();
    }
    
    @Override
    public boolean addPost(Post post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(post);
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addAuctionPost(Post post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            post.setIsAuction(true);
            session.save(post);
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean disablePost(int postId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(Post.class);
        Root root = u.from(Post.class);
        u.set("active", false);
        u.where(b.equal(root.get("id").as(Integer.class), postId));
        
        return session.createQuery(u).executeUpdate() > 0;
    }

    @Override
    public long countPosts() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        q.select(b.count(q.from(Post.class)));
        
        return session.createQuery(q).getSingleResult();
    }
    
    @Override
    public boolean updatePost(Post post, int postId){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaUpdate u = b.createCriteriaUpdate(Post.class);
        Root root = u.from(Post.class);
        u.set("image", post.getImage());
        u.set("content", post.getContent());
        u.set("price", post.getPrice());
        u.where(b.equal(root.get("id").as(Integer.class), postId));
        
        return session.createQuery(u).executeUpdate() > 0;
    }
}
