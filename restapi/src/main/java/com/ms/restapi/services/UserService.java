package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.User;
import com.ms.restapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired
    UserRepository rep;

    public List<User> getAll()
    {
        return rep.findAll();
    }

    public User getByID(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
