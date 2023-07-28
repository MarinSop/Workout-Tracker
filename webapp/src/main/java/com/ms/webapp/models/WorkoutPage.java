package com.ms.webapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPage {
    private List<Workout> content;
    private int totalPages;
    private boolean last;
    private int number;
    private int size;
}
