package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.User;
import com.ms.restapi.services.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService ser;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.ok(ser.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getByID(int id)
    {
        return ResponseEntity.ok(ser.getByID(id));
    }
}
