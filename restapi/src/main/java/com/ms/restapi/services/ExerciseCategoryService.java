package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.ExerciseCategory;
import com.ms.restapi.repositories.ExerciseCategoryRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseCategoryService {
    
    @Autowired
    ExerciseCategoryRepository rep;

    public List<ExerciseCategory> getAll()
    {
        return rep.findAll();
    }

    public ExerciseCategory getById(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ExerciseCategory addExerciseCategory(ExerciseCategory exerciseCategory)
    {
        return rep.save(exerciseCategory);
    }

    public ExerciseCategory editExerciseCategory(ExerciseCategory exerciseCategory, int id)
    {
        ExerciseCategory exisitingExerciseCategory = rep.findById(id).orElseThrow(EntityExistsException::new);
        exisitingExerciseCategory.setName(exerciseCategory.getName());
        exisitingExerciseCategory.setExercises(exerciseCategory.getExercises());
        return rep.save(exisitingExerciseCategory);
    }

    public String deleteExerciseCategory(int id)
    {
        rep.delete(rep.findById(id).orElseThrow(EntityNotFoundException::new));
        return "Exercise category deleted succesfully!";
    }
}
