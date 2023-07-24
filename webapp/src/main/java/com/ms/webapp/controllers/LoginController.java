package com.ms.webapp.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import com.ms.webapp.models.LoginRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("LoginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String postlogin(LoginRequest loginRequest, HttpServletResponse response)
    {
        try
        {
            String jwtToken = getTokenFromRequest(loginRequest);
            Cookie cookie = new Cookie("jwt_token", jwtToken);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600); 
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        catch(Exception e)
        {
            return "redirect:/login?error";
        }

        return "redirect:/home";
    }


    private String getTokenFromRequest(LoginRequest loginRequest)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(loginRequest.getUsername(),loginRequest.getPassword());
        HttpEntity<String> httprequest = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/auth/token", HttpMethod.POST,
                httprequest, String.class);
            return response.getBody();
    }


    @GetMapping("logout")
    private String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
    
        if (cookies != null) 
        {
            for (Cookie cookie : cookies) 
            {
                if (cookie.getName().equals("jwt_token")) 
                {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        return "redirect:/login?logout";
    }
}
    


