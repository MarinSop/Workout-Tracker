package com.ms.restapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.restapi.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout,Integer>{
    
        @Query("SELECT w FROM Workout w " +
        "LEFT JOIN FETCH w.workoutExercises we " +
        "LEFT JOIN FETCH we.exercise e " +
        "WHERE (w.name LIKE %:query% OR e.name LIKE %:query%) " + 
        "AND (w.user.username = :username)")
    Page<Workout> searchWorkouts(@Param("query") String query, @Param("username") String username, Pageable pageable);
}
