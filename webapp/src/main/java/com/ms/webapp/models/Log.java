package com.ms.webapp.models;

import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    
    private int id;
    
    private LocalDate date;

    private List<LogExercise> logExercises;
}
