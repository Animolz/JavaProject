/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.controller;

import com.lhn.service.StatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;
    private SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd");
        
    @GetMapping(path = {"/admin/post-summary"})
    public String postSummary(Model model){
        model.addAttribute("post", this.statsService.countPosts());
        model.addAttribute("auction", this.statsService.getAuctionPosts());
        return "post-summary";
    }
    
    
    @GetMapping(path = {"/admin/post-summary-by-time"})
    public String postSummaryByTime(Model model,
            @RequestParam(required = false) Map<String, String> params) throws ParseException{
        if(params.containsKey("start-date") && params.containsKey("end-date")){
            Date startDate = F.parse(params.getOrDefault("start-date", F.format(new Date())));
            Date endDate = F.parse(params.getOrDefault("end-date", F.format(new Date())));
            List<List<Object[]>> object = new ArrayList<>();
            object.add(this.statsService.countPosts(startDate, endDate));
            object.add(this.statsService.countAuctionPosts(startDate, endDate));
            
            model.addAttribute("object", object);
                    
            return "post-summary-by-time";
        }
        List<List<Object[]>> object = new ArrayList<>();
        Date date = new Date();
        object.add(this.statsService.countPosts(date, date));
        object.add(this.statsService.countAuctionPosts(date, date));
            
        model.addAttribute("object", object);
        return "post-summary-by-time";
    }
}
