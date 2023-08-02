package com.ms.webapp.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.ms.webapp.services.IndexService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private IndexService ser;

    @GetMapping("/home")
    public String index(Model model, HttpServletRequest req,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
            @RequestParam(defaultValue = "1") int page) {

        return ser.index(model, req, date, page);
    }

    @PostMapping("/home/log-workout")
    public String logWorkout(@RequestParam(value = "date") String date,
    @RequestParam(defaultValue = "1") int page, 
    @RequestParam(value = "workoutId") int workoutId,
    HttpServletRequest req) {

        return ser.logWorkout(date, page, workoutId, req);
    }

    @PostMapping("/home/log-exercise")
    public String logExercise(@RequestParam(value = "date") String date,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(value = "exercise-select") int exerciseId,
            @RequestParam(value = "reps") int reps,
            @RequestParam(value = "sets") int sets,
            @RequestParam(value = "weight") int weight,
            HttpServletRequest req) 
    {

        return ser.logExercise(date, page, exerciseId, reps, sets, weight, req);
    }

    @GetMapping("/home/delete-exercise")
    public String exerciseDelete(@RequestParam(value = "logId") int logId,
            @RequestParam(value="date") String date,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(value = "exerciseId") int exerciseId, HttpServletRequest req) {

        return ser.exerciseDelete(logId, date, page, exerciseId, req);
    }

}
