package com.ms.restapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.Exercise;


public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {
    
    Exercise findByName(String name);
}
