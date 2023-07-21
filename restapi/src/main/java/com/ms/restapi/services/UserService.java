package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.User;
import com.ms.restapi.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
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

    public User addUser(User user)
    {
        rep.findByUsername(user.getUsername()).ifPresent(u -> {throw new EntityExistsException("User already exists!");});
        return rep.save(user);

    }

    public User editUser(User user, int id)
    {
        User existingUser = rep.findById(id).orElseThrow(EntityExistsException::new);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setWorkouts(user.getWorkouts());
        existingUser.setLogs(user.getLogs());
        return rep.save(existingUser);
    }

    public String deleteUser(int id)
    {
        rep.delete(rep.findById(id).orElseThrow(EntityExistsException::new));
        return "User deleted succesfully";
    }
}
