package com.ms.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ms.restapi.entities.Exercise;
import com.ms.restapi.entities.User;
import com.ms.restapi.entities.Workout;
import com.ms.restapi.entities.WorkoutExercise;
import com.ms.restapi.entities.WorkoutExerciseId;
import com.ms.restapi.repositories.ExerciseRepository;
import com.ms.restapi.repositories.UserRepository;
import com.ms.restapi.repositories.WorkoutExerciseRepository;
import com.ms.restapi.repositories.WorkoutRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutService {
    
    @Autowired
    WorkoutRepository rep;
    @Autowired
    UserRepository userRep;
    @Autowired
    ExerciseRepository exerciseRep;
    @Autowired
    WorkoutExerciseRepository workoutExerciseRep;
    @Autowired
    WorkoutExerciseService workoutExerciseSer;

    public List<Workout> getAll()
    {
        return rep.findAll();
    }

        public Workout getByID(int id)
    {
        return rep.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Workout addWorkout(Workout workout, Authentication authentication)
    {
        User user = userRep.findByUsername(authentication.getName()).orElseThrow(EntityNotFoundException::new);
        workout.setUser(user);
        return rep.save(workout);
    }
     
    public Workout editWorkout(Workout workout, int workoutId)
    {

        Workout existingWorkout = rep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        existingWorkout.setName(workout.getName());
        existingWorkout.setWorkoutExercises(workoutExerciseSer.updateExercisesInWorkout(workoutId, workout.getWorkoutExercises()));
        return rep.save(existingWorkout);
    }

    public String deleteWorkout(int id)
    {
        Workout workout = rep.findById(id).orElseThrow(EntityNotFoundException::new);
        workout.getWorkoutExercises().forEach(e -> {workoutExerciseSer.delete(id, e.getExercise().getId());});
        workout.getWorkoutExercises().clear();
        rep.delete(workout);
        return "Workout deleted succesfully!";
    }

    public WorkoutExercise addExerciseToWorkout(int workoutId,int exerciseId,int sets, int reps, int weight)
    {
        Workout workout = rep.findById(workoutId).orElseThrow(EntityNotFoundException::new);
        Exercise exercise = exerciseRep.findById(exerciseId).orElseThrow(EntityNotFoundException::new);
        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setId(new WorkoutExerciseId(workoutId, exerciseId));
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(exercise);
        workoutExercise.setSets(sets);
        workoutExercise.setReps(reps);
        workoutExercise.setWeight(weight);
        
        return workoutExerciseRep.save(workoutExercise);

    }

    public Page<Workout> getWorkoutsPage(int page,int size,String query,Integer category,String sortDirection) 
    {
        Pageable pageable;

        Sort sort = Sort.by("name");
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        }
        pageable = PageRequest.of(page, size, sort);

        Page<Workout> result = rep.searchWorkouts(query, category, pageable);

        return result;
    }
}
