/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository;

import com.lhn.pojo.Like1;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatsRepository {
    List<Object[]> countPosts();
    List<Object[]> countAuctionPosts();
    List<Object[]> countPosts(Date startDate, Date endDate);
    List<Object[]> countAuctionPosts(Date startDate, Date endDate);
}
