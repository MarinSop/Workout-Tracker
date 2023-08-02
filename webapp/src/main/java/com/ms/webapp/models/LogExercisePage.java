package com.ms.webapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogExercisePage {
    
    private List<LogExercise> content;
    private int totalPages;
    private boolean last;
    private boolean first;
    private int number;
    private int size;
    
}

