/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service.impl;

import com.lhn.pojo.User;
import com.lhn.repository.UserRepository;
import com.lhn.service.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getUsers(Map<String, String> param) {
        return this.userRepository.getUsers(param);
    }
    
    public List<User> getUsersRand(User user){
        return this.userRepository.getUsersRand(user);
    }
    
    @Override
    public User getUserById(int id){
        return this.userRepository.getUserById(id);
    }

    @Override
    public boolean addUser(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(user.USER);
        user.setActive(Boolean.TRUE);
        user.setCreatedTime(new Date());
        return this.userRepository.addUser(user);
    }

    @Override
    public boolean updateUser(User user, int id) {
        return this.userRepository.updateUser(user, id);
    }

    @Override
    public boolean deleteUser(int id) {
        return this.userRepository.deleteUser(id);
    }

    @Override
    public boolean updateActive(User user) {
        return this.userRepository.updateActive(user);
    }

    @Override
    public boolean banUser(User user) {
       return this.userRepository.banUser(user);
    }

    @Override
    public boolean unbanUser(User user) {
        return this.userRepository.unbanUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        List<User> users = userRepository.getUsers(param);
        if (users.isEmpty()) 
            throw new UsernameNotFoundException("Không tồn tại!"); 
        
        User u = users.get(0);
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean getUserByUsername(String string) {
        User user = this.userRepository.getUserByUsername(string);
        if(user.getUsername().isEmpty() || user.getUsername() == null )
            return false;
        return true;
    }

    @Override
    public boolean updateReported(User user) {
        return this.userRepository.updateReported(user);
    }

}
