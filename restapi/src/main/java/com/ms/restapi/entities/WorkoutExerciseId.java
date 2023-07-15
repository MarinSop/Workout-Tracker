package com.ms.restapi.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WorkoutExerciseId  implements Serializable{

    private int workoutId;
    private int exerciseId;
    
}
