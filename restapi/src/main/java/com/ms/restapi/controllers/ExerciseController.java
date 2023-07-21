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

    @PostMapping("/exercises")
       public ResponseEntity<Exercise> addWorkout(@RequestBody Exercise exercise)
    {
        return ResponseEntity.ok(ser.addExercise(exercise));
    }
     
    @PutMapping("/exercises/{id}")
    public ResponseEntity<Exercise> editExerciseWorkout(@RequestBody Exercise exercise, @PathVariable int exerciseId)
    {
        return ResponseEntity.ok(ser.editExercise(exercise,exerciseId));
    }

    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<String> deleteExercise(int id)
    {
        ser.deleteExercise(id);
        return ResponseEntity.ok("Exercise deleted succesfully!");
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
