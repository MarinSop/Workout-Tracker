package com.ms.restapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkoutExercise {
    
    @EmbeddedId
    private WorkoutExerciseId id;

    @ManyToOne
    @MapsId("workoutId")
    @JsonIgnoreProperties("workoutExercises")
    private Workout workout;

    @ManyToOne
    @MapsId("exerciseId")
    @JsonIgnoreProperties("workoutExercises")
    private Exercise exercise;

    private int sets;
    private int reps;
    private int weight;
}
