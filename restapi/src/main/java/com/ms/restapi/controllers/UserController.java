package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<User> getByID(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.getByID(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        return ResponseEntity.ok(ser.addUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable int id)
    {
        return ResponseEntity.ok(ser.editUser(user,id));
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.deleteUser(id));
    }
}
