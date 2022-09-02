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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFullname", query = "SELECT u FROM User u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByAbout", query = "SELECT u FROM User u WHERE u.about = :about"),
    @NamedQuery(name = "User.findByCreatedTime", query = "SELECT u FROM User u WHERE u.createdTime = :createdTime"),
    @NamedQuery(name = "User.findByIsReported", query = "SELECT u FROM User u WHERE u.isReported = :isReported"),
    @NamedQuery(name = "User.findByIsBanned", query = "SELECT u FROM User u WHERE u.isBanned = :isBanned"),
    @NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByFacebook", query = "SELECT u FROM User u WHERE u.facebook = :facebook"),
    @NamedQuery(name = "User.findByInstagram", query = "SELECT u FROM User u WHERE u.instagram = :instagram")})
public class User implements Serializable {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{user.fullname.null}")
    @Size.List({
        @Size(min = 1, message = "{user.fullname.min}"),
        @Size(max = 45, message = "{user.fullname.max}")
    })
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @NotNull(message = "{user.username.null}")
    @Size.List({
        @Size(min = 1, message = "{user.username.min}"),
        @Size(max = 20, message = "{user.username.max}")
    })
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull(message = "{user.password.null}")
    @Size.List({
        @Size(min = 1, message = "{user.password.min}"),
        @Size(max = 150, message = "{user.password.max}")
    })
    @Column(name = "password")
    private String password;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="{user.email.format}")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100, message = "{user.email.size}")
    @Column(name = "email")
    private String email;
    @Pattern(regexp="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message="{user.phone.format}")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull(message = "{user.phone.null}")
    @Size.List({
        @Size(min = 1, message = "{user.phone.min}"),
        @Size(max = 12, message = "{user.phone.max}")
    })
    @Column(name = "phone")
    private String phone;
    @Size(max = 150)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255, message = "{user.about.max}")
    @Column(name = "about")
    private String about;
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(name = "is_reported")
    private Boolean isReported;
    @Column(name = "is_banned")
    private Boolean isBanned;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 100)
    @Column(name = "facebook")
    private String facebook;
    @Size(max = 100)
    @Column(name = "instagram")
    private String instagram;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Post> postSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Like1> like1Set;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<AuctionPartis> auctionPartisSet;
    @Transient
    @JsonIgnore
    @Nullable
    private String confirmPassword;
    @Transient
    @JsonIgnore
    @Nullable
    private MultipartFile file;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String fullname, String username, String password, String phone, String userRole) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getIsReported() {
        return isReported;
    }

    public void setIsReported(Boolean isReported) {
        this.isReported = isReported;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    @XmlTransient
    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lhn.pojo.User[ id=" + id + " ]";
    }
    
}
