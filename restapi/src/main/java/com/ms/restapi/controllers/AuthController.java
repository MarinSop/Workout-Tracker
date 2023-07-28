package com.ms.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.User;
import com.ms.restapi.services.TokenService;
import com.ms.restapi.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   TokenService tokenService;

   @Autowired
   UserService userSer;

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/register")
    public ResponseEntity<User> creatingNewUser(@RequestBody User user)
    {
        return ResponseEntity.ok(userSer.addUser(user));
    }
}
