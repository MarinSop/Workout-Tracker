package com.ms.restapi.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.restapi.entities.Log;
import com.ms.restapi.entities.LogExercise;


public interface LogRepository extends JpaRepository<Log,Integer>{
    
    List<Log> findAllByUserUsername(String username);
    Optional<Log> findByDateAndUserUsername(LocalDate date, String username);
    Page<LogExercise> findAllExercisesByDateAndUserUsername(String username, LocalDate date, Pageable pageable);

    @Query("SELECT COUNT(distinct l) FROM Log l INNER JOIN l.logExercises.exercise e WHERE l.user.username = :username AND MONTH(l.date) = :month")
    int countLogsForUserWithExercisesInMonth(String username, int month);

    @Query("SELECT COUNT(distinct l) FROM Log l INNER JOIN l.logExercises.exercise e WHERE l.user.username = :username AND YEAR(l.date) = :year")
    int countLogsForUserWithExercisesInYear(String username, int year);
}
