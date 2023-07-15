package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.Exercise;
import com.ms.restapi.services.ExerciseService;

@RestController
public class ExerciseController {
    
    @Autowired
    ExerciseService ser;

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getAll()
    {
        return ResponseEntity.ok(ser.getAll());
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> getByID(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.getByID(id));
    }

    @GetMapping("/exercises/workout/{id}")
    public ResponseEntity<List<Exercise>> getAllByWorkout(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.findAllByWorkout(id));
    }

    @GetMapping("/exercises/workouts/{id}")
    public ResponseEntity<List<Exercise>> getAllByWorkoutAndWorkoutUser(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.findAllByWorkoutAndUser(id));
    }



}
