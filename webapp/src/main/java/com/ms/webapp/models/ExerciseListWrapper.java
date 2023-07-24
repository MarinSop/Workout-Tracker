package com.ms.webapp.models;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExerciseListWrapper {
    private List<ExerciseWorkout> exercises;


    public List<ExerciseWorkout> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseWorkout> exercises) {
        this.exercises = exercises;
    }
}
