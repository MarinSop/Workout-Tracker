package com.ms.restapi.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.restapi.entities.LogExercise;
import com.ms.restapi.entities.LogExerciseId;

public interface LogExerciseRepository extends JpaRepository<LogExercise,Integer> {
    
     Page<LogExercise> findAllByLogUserUsernameAndLogDate(String username, LocalDate date, Pageable pageable);
     Optional<LogExercise> findByIdAndLogUserUsername(LogExerciseId id, String username);
     int countByLogUserUsername(String username);
}
