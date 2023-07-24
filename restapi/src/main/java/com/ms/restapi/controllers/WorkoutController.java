package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.Workout;
import com.ms.restapi.entities.WorkoutExercise;
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
       public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout, Authentication authentication)
    {
        return ResponseEntity.ok(ser.addWorkout(workout,authentication));
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

    @PostMapping("/workouts/{workoutId}/exercises/{exercisesId}/{sets}/{reps}/{weight}")
    public ResponseEntity<WorkoutExercise> addExerciseToWorkout(@PathVariable int workoutId,@PathVariable int exercisesId,@PathVariable int sets,@PathVariable int reps,@PathVariable int weight)
    {
        return ResponseEntity.ok(ser.addExerciseToWorkout(workoutId,exercisesId,sets,reps,weight));

    }
}
