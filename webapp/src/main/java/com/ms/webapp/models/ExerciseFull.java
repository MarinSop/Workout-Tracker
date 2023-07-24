package com.ms.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseFull {
    Exercise exercise;
    int reps;
    int sets;
    int weight;
}
