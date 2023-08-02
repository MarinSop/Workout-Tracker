package com.ms.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.webapp.models.ExerciseListWrapper;
import com.ms.webapp.services.WorkoutsService;
import com.ms.webapp.models.Workout;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WorkoutsController {
    
    @Autowired
    private WorkoutsService ser;
    
    @GetMapping("/workouts")
    public String myWorkoutsWithPages(Model model, HttpServletRequest req, @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "") String query, @RequestParam(defaultValue = "asc") String sort)
    {
        return ser.myWorkoutsWithPages(model, req, page, query, sort);
    }


    @GetMapping("/workouts/new-workout")
    public String newWorkoutPage(Model model, HttpServletRequest req)
    {
        return ser.newWorkoutPage(model, req);
    }

    @PostMapping("/workouts/new-workout")
    public String newWorkoutAdd( HttpServletRequest req,@RequestParam(value="workoutName") String name,@ModelAttribute ExerciseListWrapper exercises)
    {
        return ser.newWorkoutAdd(req, name, exercises);
    }

    @GetMapping("/workouts/edit-workout/{id}")
    public String editWorkoutPage(@PathVariable int id, Model model, HttpServletRequest req)
    {
        return ser.editWorkoutPage(id, model, req);
    }

    @PostMapping("/workouts/edit-workout/{id}")
    public String editWorkout(@ModelAttribute Workout workout, @PathVariable int id, HttpServletRequest req)
    {
        return ser.editWorkout(workout, id, req);
    }


    @GetMapping("/workouts/delete-workout/{id}")
    public String deleteWorkout(@PathVariable int id, HttpServletRequest req)
    {
        return ser.deleteWorkout(id, req);
    }

}
