package com.ms.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ms.webapp.services.StatisticsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StatisticsController {
    
    @Autowired
    private StatisticsService ser;

    @GetMapping("/statistics")
    public String statisticsPage(Model model, HttpServletRequest req)
    {
        return ser.statisticsPage(model,req);
    }

}
