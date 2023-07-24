package com.ms.restapi.models;

import com.ms.restapi.entities.Exercise;

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
