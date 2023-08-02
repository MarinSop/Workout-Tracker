package com.ms.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ms.webapp.models.LoginRequest;
import com.ms.webapp.services.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class LoginController {

    @Autowired
    LoginService ser;

    @GetMapping("/login")
    public String login(Model model) {
        return ser.login(model);
    }

    @PostMapping("/login")
    public String postlogin(LoginRequest loginRequest, HttpServletResponse response)
    {

        return ser.postlogin(loginRequest, response);
    }


    @GetMapping("logout")
    private String logout(HttpServletRequest request, HttpServletResponse response)
    {
        return ser.logout(request, response);
    }


    @GetMapping("/register")
    public String registerPage(HttpServletRequest request, Model model)
    {
        return ser.registerPage(request, model);
    }

    @PostMapping("/register")
    public String registerAccount(LoginRequest loginRequest)
    {
        return ser.registerAccount(loginRequest);
    }
    
}

