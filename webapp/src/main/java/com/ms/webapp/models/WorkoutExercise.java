package com.ms.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExercise {
    WorkoutExerciseId id;
    @JsonIgnore
    Workout workout;
    Exercise exercise;
    int sets;
    int reps;
    int weight;
}
