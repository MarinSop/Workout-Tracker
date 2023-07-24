package com.ms.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.User;
import com.ms.restapi.entities.Workout;
import com.ms.restapi.entities.WorkoutExercise;


public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise,Integer> {
    List<WorkoutExercise> findAllByWorkout(Workout workout);
    List<WorkoutExercise> findAllByWorkoutAndWorkoutUser(Workout workout, User user);
}
