package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Workout;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutService {
    
    @Autowired
    WorkoutRepository rep;

    public List<Workout> getAll()
    {
        return rep.findAll();
    }

        public Workout getByID(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Workout addWorkout(Workout workout)
    {
        return rep.save(workout);
    }
     
    public Workout editWorkout(Workout workout, int workoutId)
    {
        Workout existingWorkout = rep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        existingWorkout.setName(workout.getName());
        existingWorkout.setUser(workout.getUser());
        existingWorkout.setLogs(workout.getLogs());
        existingWorkout.setWorkoutExercises(workout.getWorkoutExercises());
        return rep.save(existingWorkout);
    }

    public String deleteWorkout(int id)
    {
        rep.delete(rep.findById(id).orElseThrow(EntityNotFoundException::new));
        return "Workout deleted succesfully!";
    }
}
