package com.ms.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseWorkout {
    int id;
    int sets;
    int reps;
    int weight;
}
