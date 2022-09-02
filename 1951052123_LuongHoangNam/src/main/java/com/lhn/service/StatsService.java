/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.service;

import com.lhn.pojo.Like1;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatsService {
    List<Object[]> countPosts();
    List<Object[]> getAuctionPosts();
    List<Object[]> countPosts(Date startDate, Date endDate);
    List<Object[]> countAuctionPosts(Date startDate, Date endDate);
}
