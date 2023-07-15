package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.Workout;
import com.ms.restapi.services.WorkoutService;

@RestController
public class WorkoutController {
    
    @Autowired
    WorkoutService ser;

    @GetMapping("/workouts")
    public ResponseEntity<List<Workout>> getAll()
    {
        return ResponseEntity.ok(ser.getAll());
    }

    @GetMapping("/workouts/{id}")
    public ResponseEntity<Workout> getByID(int id)
    {
        return ResponseEntity.ok(ser.getByID(id));
    }
}
