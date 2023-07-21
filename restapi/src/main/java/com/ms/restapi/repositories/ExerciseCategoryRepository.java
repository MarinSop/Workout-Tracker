package com.ms.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.ExerciseCategory;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory,Integer> {
    
}
