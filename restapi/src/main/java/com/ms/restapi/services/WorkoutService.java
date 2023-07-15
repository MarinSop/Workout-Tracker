package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Workout;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutService {
    
    @Autowired
    WorkoutRepository rep;

    public List<Workout> getAll()
    {
        return rep.findAll();
    }

        public Workout getByID(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
