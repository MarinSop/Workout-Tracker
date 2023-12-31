package com.ms.restapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Exercise;
import com.ms.restapi.entities.Workout;
import com.ms.restapi.entities.WorkoutExercise;
import com.ms.restapi.entities.WorkoutExerciseId;
import com.ms.restapi.repositories.ExerciseRepository;
import com.ms.restapi.repositories.WorkoutExerciseRepository;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutExerciseService {

    @Autowired
    WorkoutExerciseRepository rep;

    @Autowired
    WorkoutRepository workoutRep;

    @Autowired
    ExerciseRepository exeriseRep;

    public List<WorkoutExercise> updateExercisesInWorkout(int id, List<WorkoutExercise> list, Authentication auth)
    {

        Workout workout = workoutRep.findByIdAndUserUsername(id,auth.getName()).orElseThrow(EntityNotFoundException::new);
        list.forEach(e -> {
            Exercise exercise = exeriseRep.findById(e.getExercise().getId()).orElseThrow(EntityNotFoundException::new);
            e.setId(new WorkoutExerciseId(id, e.getExercise().getId()));
            e.setWorkout(workout);
            e.setExercise(exercise);
            rep.save(e);
        });

        List<WorkoutExercise> delete = workout.getWorkoutExercises().stream().filter(e -> !list.contains(e)).collect(Collectors.toList());
        delete.forEach(e -> {rep.delete(e);});
        return list;
    }

    public String delete(int workoutId, int exerciseId)
    {
        WorkoutExercise workutExercise = rep.findByIdWorkoutIdAndIdExerciseId(workoutId, exerciseId).orElseThrow(EntityNotFoundException::new);
        rep.delete(workutExercise);
        return "Success";
    }

    


    
}
