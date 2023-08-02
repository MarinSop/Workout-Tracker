package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.Workout;
import com.ms.restapi.entities.WorkoutExercise;
import com.ms.restapi.services.WorkoutService;

@RestController
public class WorkoutController {
    
    @Autowired
    WorkoutService ser;

    @GetMapping("/workouts")
    public ResponseEntity<List<Workout>> getAll(Authentication auth)
    {
        return ResponseEntity.ok(ser.getAll(auth));
    }

    @GetMapping("/workouts/{id}")
    public ResponseEntity<Workout> getByID(@PathVariable int id,Authentication auth)
    {
        return ResponseEntity.ok(ser.getByID(id,auth));
    }

    @PostMapping("/workouts")
       public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout, Authentication auth)
    {
        return ResponseEntity.ok(ser.addWorkout(workout,auth));
    }
     
    @PutMapping("/workouts/{workoutId}")
    public ResponseEntity<Workout> editWorkout(@RequestBody Workout workout, @PathVariable int workoutId, Authentication auth)
    {
        return ResponseEntity.ok(ser.editWorkout(workout,workoutId,auth));
    }

    @DeleteMapping("/workouts/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable int id, Authentication auth)
    {
        ser.deleteWorkout(id,auth);
        return ResponseEntity.ok("Workout deleted succesfully!");
    }

    @PostMapping("/workouts/{workoutId}/exercises/{exercisesId}/{sets}/{reps}/{weight}")
    public ResponseEntity<WorkoutExercise> addExerciseToWorkout(@PathVariable int workoutId,@PathVariable int exercisesId,@PathVariable int sets,@PathVariable int reps,@PathVariable int weight, Authentication auth)
    {
        return ResponseEntity.ok(ser.addExerciseToWorkout(workoutId,exercisesId,sets,reps,weight, auth));

    }

    @GetMapping("/workouts/pages")
    public ResponseEntity<Page<Workout>> getWorkouts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size,
        @RequestParam(required = false) String query,
        @RequestParam(defaultValue = "asc") String sortDirection,
        Authentication auth){

    return ResponseEntity.ok(ser.getWorkoutsPage(page, size, query, sortDirection, auth));
    }
}
