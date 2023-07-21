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
    public ResponseEntity<Workout> getByID(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.getByID(id));
    }

    @PostMapping("/workouts")
       public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout)
    {
        return ResponseEntity.ok(ser.addWorkout(workout));
    }
     
    @PutMapping("/workouts/{id}")
    public ResponseEntity<Workout> editWorkout(@RequestBody Workout workout, @PathVariable int workoutId)
    {
        return ResponseEntity.ok(ser.editWorkout(workout,workoutId));
    }

    @DeleteMapping("/workouts/{id}")
    public ResponseEntity<String> deleteWorkout(int id)
    {
        ser.deleteWorkout(id);
        return ResponseEntity.ok("Workout deleted succesfully!");
    }
}
