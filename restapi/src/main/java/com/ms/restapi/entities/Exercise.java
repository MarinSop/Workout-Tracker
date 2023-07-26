package com.ms.restapi.entities;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutExercises;

    @OneToMany(mappedBy = "exercise")
    private List<LogExercise> logExercises;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExerciseCategory category;
    
}
