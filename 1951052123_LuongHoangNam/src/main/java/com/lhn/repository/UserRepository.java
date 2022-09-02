/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository;

import com.lhn.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface UserRepository {
    List<User> getUsers(Map<String, String> param);
    List<User> getUsersRand(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean addUser(User user);
    boolean updateUser(User user, int id);
    boolean deleteUser(int id);
    boolean updateActive(User user);
    boolean banUser(User user);
    boolean unbanUser(User user);
    boolean updateReported(User user);
}
