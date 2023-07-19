package com.ms.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {


    @ResponseBody
    @GetMapping("/")
    public String index()
    {
        
        return "HEKLPLPA";
    }
}
