/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service;

import com.lhn.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers(Map<String, String> param);
    List<User> getUsersRand(User user);
    User getUserById(int id);
    boolean getUserByUsername(String string);
    boolean addUser(User user);
    boolean updateUser(User user, int id);
    boolean deleteUser(int id);
    boolean updateActive(User user);
    boolean banUser(User user);
    boolean unbanUser(User user);
    boolean updateReported(User user);
}
