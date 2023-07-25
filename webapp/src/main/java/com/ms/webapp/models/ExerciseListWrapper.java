package com.ms.webapp.models;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExerciseListWrapper {
    private List<WorkoutExercise> exercises;


    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }
}
