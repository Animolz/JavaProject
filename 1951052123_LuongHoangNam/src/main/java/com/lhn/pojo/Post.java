/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.annotation.Nullable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByContent", query = "SELECT p FROM Post p WHERE p.content = :content"),
    @NamedQuery(name = "Post.findByImage", query = "SELECT p FROM Post p WHERE p.image = :image"),
    @NamedQuery(name = "Post.findByPostedDate", query = "SELECT p FROM Post p WHERE p.postedDate = :postedDate"),
    @NamedQuery(name = "Post.findByIsAuction", query = "SELECT p FROM Post p WHERE p.isAuction = :isAuction"),
    @NamedQuery(name = "Post.findByActive", query = "SELECT p FROM Post p WHERE p.active = :active"),
    @NamedQuery(name = "Post.findByPrice", query = "SELECT p FROM Post p WHERE p.price = :price")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{post.content.null}")
    @Size.List({
        @Size(min = 1, message = "{post.content.min}"),
        @Size(max = 200, message = "{post.content.max}")
    })
    @Column(name = "content")
    private String content;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @Column(name = "posted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "is_auction")
    private Boolean isAuction;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "price")
    private Long price;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<Like1> like1Set;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<AuctionPartis> auctionPartisSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<PostTag> postTagSet;
    @Transient
    @JsonIgnore
    @Nullable
    private MultipartFile file;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Boolean getIsAuction() {
        return isAuction;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setIsAuction(Boolean isAuction) {
        this.isAuction = isAuction;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Set<Like1> getLike1Set() {
        return like1Set;
    }

    public void setLike1Set(Set<Like1> like1Set) {
        this.like1Set = like1Set;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<AuctionPartis> getAuctionPartisSet() {
        return auctionPartisSet;
    }

    public void setAuctionPartisSet(Set<AuctionPartis> auctionPartisSet) {
        this.auctionPartisSet = auctionPartisSet;
    }

    @XmlTransient
    public Set<PostTag> getPostTagSet() {
        return postTagSet;
    }

    public void setPostTagSet(Set<PostTag> postTagSet) {
        this.postTagSet = postTagSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lhn.pojo.Post[ id=" + id + " ]";
    }
    
}
