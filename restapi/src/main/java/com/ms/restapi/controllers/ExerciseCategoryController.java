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

import com.ms.restapi.entities.ExerciseCategory;
import com.ms.restapi.services.ExerciseCategoryService;

@RestController
public class ExerciseCategoryController {

    @Autowired
    ExerciseCategoryService ser;

    @GetMapping("/exercisecategories")
    public ResponseEntity<List<ExerciseCategory>> getAll()
    {
        return ResponseEntity.ok(ser.getAll());
    }

    @GetMapping("exercisecategories/{id}")
    public ResponseEntity<ExerciseCategory> getById(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.getById(id));
    }

    @PostMapping("exercisecategories")
    public ResponseEntity<ExerciseCategory> addExerciseCategory(@RequestBody ExerciseCategory exerciseCategory)
    {
        return ResponseEntity.ok(ser.addExerciseCategory(exerciseCategory));
    }

    @PutMapping("exercisecategories/{id}")
    public ResponseEntity<ExerciseCategory> editExerciseCategory(@RequestBody ExerciseCategory exerciseCategory, @PathVariable int id)
    {
        return ResponseEntity.ok(ser.editExerciseCategory(exerciseCategory,id));
    }

    @DeleteMapping("/exercisecategories/{id}")
    public ResponseEntity<String> deleteExerciseCategory(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.deleteExerciseCategory(id));
    }  
}
