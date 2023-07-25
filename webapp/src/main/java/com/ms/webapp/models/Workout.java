package com.ms.webapp.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    
    int id;
    String name;
    List<WorkoutExercise> workoutExercises = new ArrayList<WorkoutExercise>();
}
