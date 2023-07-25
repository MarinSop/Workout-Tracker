package com.ms.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.WorkoutExercise;
import com.ms.restapi.services.WorkoutExerciseService;



@RestController
public class WorkoutExerciseController {
    
    @Autowired
    WorkoutExerciseService ser;

    @PutMapping("/workout/{id}/exercises")
    public ResponseEntity<List<WorkoutExercise>> updateExercisesInWorkout(@PathVariable int id, @RequestBody List<WorkoutExercise> list)
    {
        return ResponseEntity.ok(ser.updateExercisesInWorkout(id,list));
    }
}
