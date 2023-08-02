package com.ms.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogExercise {

    private LogExerciseId id;

    private Log log;


    private Exercise exercise;

    private int sets;
    private int reps;
    private int weight;
}
