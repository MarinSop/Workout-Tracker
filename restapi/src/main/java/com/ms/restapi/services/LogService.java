package com.ms.restapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Log;
import com.ms.restapi.entities.LogExercise;
import com.ms.restapi.entities.LogExerciseId;
import com.ms.restapi.entities.User;
import com.ms.restapi.entities.Workout;
import com.ms.restapi.repositories.ExerciseRepository;
import com.ms.restapi.repositories.LogExerciseRepository;
import com.ms.restapi.repositories.LogRepository;
import com.ms.restapi.repositories.UserRepository;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LogService {
    
    @Autowired
    private LogRepository rep;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private WorkoutRepository workoutRep;

    @Autowired
    private ExerciseRepository exerciseRep;


    @Autowired
    private LogExerciseRepository logExerciseRep;

    public List<Log> getAllLogs(Authentication auth)
    {
        return rep.findAllByUserUsername(auth.getName());
    }

    public Log getById(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Log getByDate(LocalDate date, Authentication auth)
    {
        return rep.findByDateAndUserUsername(date,auth.getName()).orElseThrow(EntityNotFoundException::new);
    }

    public Log addLog(Log log, Authentication auth)
    {
        rep.findByDateAndUserUsername(log.getDate(),auth.getName()).ifPresent(l -> {throw new EntityExistsException();});
        log.setUser(userRep.findByUsername(auth.getName()).orElseThrow(EntityNotFoundException::new));
        return rep.save(log);
    }

    public Log addWorkoutToLog(LocalDate date, int workoutId, Authentication auth)
    {
        User user = userRep.findByUsername(auth.getName()).orElseThrow(EntityNotFoundException::new);
        Workout workout = workoutRep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        Optional<Log> log = rep.findByDateAndUserUsername(date,auth.getName());
        if(log.isPresent())
        {
            Log existingLog = log.get();
            workout.getWorkoutExercises().forEach(e -> {
                LogExercise logExercise = new LogExercise();
                LogExerciseId id = new LogExerciseId(existingLog.getId(),e.getExercise().getId());
                logExercise.setId(id);
                logExercise.setLog(existingLog);
                logExercise.setExercise(e.getExercise());
                logExercise.setReps(e.getReps());
                logExercise.setSets(e.getSets());
                logExercise.setWeight(e.getWeight());
                logExerciseRep.save(logExercise);
            });

            return rep.save(existingLog);
        }
        else
        {
            Log newLog = new Log();
            newLog.setUser(user);
            newLog.setDate(date);
            newLog.setLogExercises(new ArrayList<LogExercise>());
            final Log savedLog = rep.save(newLog);
            workout.getWorkoutExercises().forEach(e -> {
                LogExercise logExercise = new LogExercise();
                LogExerciseId id = new LogExerciseId(savedLog.getId(),e.getExercise().getId());
                logExercise.setId(id);
                logExercise.setLog(newLog);
                logExercise.setExercise(e.getExercise());
                logExercise.setReps(e.getReps());
                logExercise.setSets(e.getSets());
                logExercise.setWeight(e.getWeight());
                logExerciseRep.save(logExercise);
            });

            return newLog;
        }
    }

    public Log addExerciseToLog(LocalDate date, LogExercise exercise, Authentication auth)
    {
        User user = userRep.findByUsername(auth.getName()).orElseThrow(EntityNotFoundException::new);
        exercise.setExercise(exerciseRep.findById(exercise.getId().getExerciseId()).orElseThrow(EntityNotFoundException::new));
        Optional<Log> log = rep.findByDateAndUserUsername(date,auth.getName());
        if(log.isPresent())
        {
            Log existingLog = log.get();
            exercise.getId().setLogId(existingLog.getId());
            exercise.setLog(existingLog);
            logExerciseRep.save(exercise);
            existingLog.getLogExercises().add(exercise);
            return existingLog;
        }
        else
        {
            Log newLog = new Log();
            newLog.setDate(date);
            newLog.setUser(user);
            newLog.setLogExercises(new ArrayList<LogExercise>());
            newLog = rep.save(newLog);
            exercise.setId(new LogExerciseId(newLog.getId(), exercise.getExercise().getId()));
            exercise.setLog(newLog);
            logExerciseRep.save(exercise);
            return newLog;
        }
    }

    public Page<LogExercise> getAllExercisesInLog(LocalDate date, Authentication auth, int page,int size,String sortDirection) 
    {
        Pageable pageable;

        Sort sort = Sort.by("id");
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        }
        pageable = PageRequest.of(page, size, sort);

        Page<LogExercise> result = logExerciseRep.findAllByLogUserUsernameAndLogDate(auth.getName(), date, pageable);

        return result;
    }

    public String deleteLogExercise(int logId, int exerciseId, Authentication auth)
    {
        LogExerciseId id = new LogExerciseId(logId, exerciseId);
        LogExercise logExercise = logExerciseRep.findByIdAndLogUserUsername(id, auth.getName()).orElseThrow(EntityNotFoundException::new);
        logExerciseRep.delete(logExercise);
        return "Succes";
    }

    public Integer getCountOfExercisesInMonth(int month, Authentication auth)
    {
        return rep.countLogsForUserWithExercisesInMonth(auth.getName(),month);
    }

    public Integer getCountOfExercisesInYear(int year, Authentication auth)
    {
        return rep.countLogsForUserWithExercisesInYear(auth.getName(),year);
    }

    public Integer countLogExercises(Authentication auth)
    {
        return logExerciseRep.countByLogUserUsername(auth.getName());
    }

}
