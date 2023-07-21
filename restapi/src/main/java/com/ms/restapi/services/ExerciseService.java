package com.ms.restapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Exercise;
import com.ms.restapi.entities.User;
import com.ms.restapi.entities.Workout;
import com.ms.restapi.repositories.ExerciseRepository;
import com.ms.restapi.repositories.UserRepository;
import com.ms.restapi.repositories.WorkoutExerciseRepository;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseService {
    
    @Autowired
    ExerciseRepository rep;

    @Autowired
    WorkoutExerciseRepository workExerRep;

    @Autowired
    WorkoutRepository workRep;

    @Autowired
    UserRepository userRep;

    public List<Exercise> getAll()
    {
        return rep.findAll();
    }

    public Exercise getByID(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Exercise addExercise(Exercise exercise)
    {
        return rep.save(exercise);
    }

    public Exercise editExercise(Exercise exercise, int exerciseId)
    {
        Exercise existingExercise = rep.findById(exerciseId).orElseThrow(EntityNotFoundException::new);
        existingExercise.setName(exercise.getName());
        existingExercise.setCategory(exercise.getCategory());
        existingExercise.setLogExercises(exercise.getLogExercises());
        existingExercise.setWorkoutExercises(exercise.getWorkoutExercises());
        return rep.save(existingExercise);

    }

    public String deleteExercise(int id)
    {
        rep.delete(rep.findById(id).orElseThrow(EntityNotFoundException::new));
        return "Exercise deleted succesfully!";
    }


    public List<Exercise> findAllByWorkout(int workoutId)
    {
        Workout workout = workRep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        List<Exercise> exercises = new ArrayList<Exercise>();
        workExerRep.findAllByWorkout(workout).forEach(exer -> {
            exercises.add(exer.getExercise());
        });
        return exercises;
    }

    public List<Exercise> findAllByWorkoutAndUser(int workoutId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRep.findByUsername(authentication.getName()).orElseThrow(EntityNotFoundException::new);
        Workout workout = workRep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        List<Exercise> exercises = new ArrayList<Exercise>();
        workExerRep.findAllByWorkoutAndWorkoutUser(workout, user).forEach(exer -> exercises.add(exer.getExercise()));
        return exercises;
    }


}
