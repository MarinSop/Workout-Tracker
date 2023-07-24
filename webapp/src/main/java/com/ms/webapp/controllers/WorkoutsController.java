package com.ms.webapp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ms.webapp.models.Exercise;
import com.ms.webapp.models.ExerciseCategory;
import com.ms.webapp.models.ExerciseFull;
import com.ms.webapp.models.ExerciseListWrapper;
import com.ms.webapp.models.Workout;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WorkoutsController {
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/workouts")
    public String myWorkoutsPage(Model model, HttpServletRequest req)
    {

        ResponseEntity<List<Workout>> workoutResponse = restTemplate.exchange("http://localhost:8080/workouts",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<Workout>>(){});
        List<Workout> workouts = workoutResponse.getBody();

        workouts.forEach(work -> {
        ResponseEntity<List<ExerciseFull>> exercisesResponse = restTemplate.exchange("http://localhost:8080/exercises/workouts/"+ work.getId()+"/full",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<ExerciseFull>>(){});
        work.setFullExercises(exercisesResponse.getBody());
        });
        model.addAttribute("workouts", workoutResponse.getBody());


        return "workouts";
    }


    @GetMapping("/workouts/new-workout")
    public String newWorkoutPage(Model model, HttpServletRequest req)
    {
        ResponseEntity<List<ExerciseCategory>> exerciseCategoryResponse = restTemplate.exchange("http://localhost:8080/exercisecategories",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<ExerciseCategory>>(){});

        model.addAttribute("categories", exerciseCategoryResponse.getBody());

        ResponseEntity<List<Exercise>> exercisesResponse = restTemplate.exchange("http://localhost:8080/exercises",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<Exercise>>(){});

        model.addAttribute("exercises", exercisesResponse.getBody());


        return "new-workout";
    }

    @PostMapping("/workouts/new-workout")
    public String newWorkoutAdd( HttpServletRequest req,@RequestParam(value="workoutName") String name,@ModelAttribute ExerciseListWrapper exercises)
    {
        Workout workout = new Workout();
        workout.setName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(req));
        HttpEntity<Workout> request = new HttpEntity<>(workout, headers);
        ResponseEntity<Workout> workoutResponse = restTemplate.postForEntity("http://localhost:8080/workouts", request,Workout.class);
        exercises.getExercises().forEach(exer -> {
            restTemplate.postForLocation("http://localhost:8080/workouts/"+ workoutResponse.getBody().getId() + "/exercises/" + exer.getId() + "/"+exer.getSets()+"/"+ exer.getReps() +"/"+ exer.getWeight(), getCookieHeader(req));
        });
        return "redirect:/workouts";
    }

    HttpEntity<String> getCookieHeader(HttpServletRequest req)
    {
        Cookie [] cookies = req.getCookies();
        String token = Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals("jwt_token"))
        .map(Cookie::getValue)
        .findFirst().get();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return requestEntity;
    }

    String getToken(HttpServletRequest req)
    {
        Cookie [] cookies = req.getCookies();
        String token = Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals("jwt_token"))
        .map(Cookie::getValue)
        .findFirst().get();
        return token;
    }
}
