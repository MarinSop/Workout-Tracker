package com.ms.webapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Exercise {
    int id;
    String name;
    ExerciseCategory category;

}
