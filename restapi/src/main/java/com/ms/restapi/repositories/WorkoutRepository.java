package com.ms.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.Exercise;
import com.ms.restapi.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout,Integer>{
    
        Exercise findByName(String name);

}
