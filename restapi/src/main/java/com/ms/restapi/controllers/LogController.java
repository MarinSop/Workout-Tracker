package com.ms.restapi.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.restapi.entities.Log;
import com.ms.restapi.entities.LogExercise;
import com.ms.restapi.services.LogService;


@RestController
public class LogController {
    @Autowired
    private LogService ser;

    @GetMapping("/logs/date/{date}")
    public ResponseEntity<Log> findByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
    Authentication auth)
    {
        return ResponseEntity.ok(ser.getByDate(date,auth));
    }
    @GetMapping("/logs")
    public ResponseEntity<List<Log>> getAllLogs(Authentication auth)
    {
        return ResponseEntity.ok(ser.getAllLogs(auth));
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<Log> findById(@PathVariable int id)
    {
        return ResponseEntity.ok(ser.getById(id));
    }


    @PostMapping("/logs")
    public ResponseEntity<Log> addLog(@RequestBody Log log, Authentication auth)
    {
        return ResponseEntity.ok(ser.addLog(log, auth));
    }

    @PostMapping("/logs/{date}/workouts/{workoutId}")
    public Log addWorkoutToLog(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
     @PathVariable int workoutId, Authentication auth)
    {
        return ser.addWorkoutToLog(date, workoutId, auth);
    }

    @PostMapping("/logs/{date}/exercises")
    public Log addExerciseToLog(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
    @RequestBody LogExercise exercise, Authentication auth)
    {
        return ser.addExerciseToLog(date, exercise, auth);
    }

    @GetMapping("/logs/{date}/exercises/pages")
    public ResponseEntity<Page<LogExercise>> getAllExercisesInLog(
    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
    Authentication auth,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "asc") String sortDirection)
    {
        return ResponseEntity.ok(ser.getAllExercisesInLog(date, auth, page, size, sortDirection));
    }

    @DeleteMapping("/logs/exercises")
    public ResponseEntity<String> deleteLogExercise(@RequestParam(value="logId") int logId,@RequestParam(value="exerciseId") int exerciseId, Authentication auth)
    {
        return ResponseEntity.ok(ser.deleteLogExercise(logId, exerciseId, auth));
    }

    @GetMapping("/logs/month/{month}/exercises")
    public ResponseEntity<Integer> getCountOfExercisesInMonth(@PathVariable int month, Authentication auth)
    {
        return ResponseEntity.ok(ser.getCountOfExercisesInMonth(month, auth));
    }

    @GetMapping("/logs/year/{year}/exercises")
    public ResponseEntity<Integer> getCountOfExercisesInYear(@PathVariable int year, Authentication auth)
    {
        return ResponseEntity.ok(ser.getCountOfExercisesInYear(year, auth));
    }

    @GetMapping("/logs/all-exercises-done")
    public ResponseEntity<Integer> countLogExercises(Authentication auth)
    {
        return ResponseEntity.ok(ser.countLogExercises(auth));
    }

}
