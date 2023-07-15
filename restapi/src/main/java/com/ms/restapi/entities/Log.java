package com.ms.restapi.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "log")
    private List<LogExercise> logExercises;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "log_workout", 
      joinColumns = @JoinColumn(name = "workout_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "log_id", 
      referencedColumnName = "id"))
    private List<Workout> workouts;
    


}
