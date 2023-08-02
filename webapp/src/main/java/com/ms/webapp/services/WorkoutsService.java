package com.ms.webapp.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.ms.webapp.models.Exercise;
import com.ms.webapp.models.ExerciseCategory;
import com.ms.webapp.models.ExerciseListWrapper;
import com.ms.webapp.models.Workout;
import com.ms.webapp.models.WorkoutExercise;
import com.ms.webapp.models.WorkoutPage;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class WorkoutsService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public String myWorkoutsWithPages(Model model, HttpServletRequest req, int page,String query, String sort)
    {
        String url ="http://localhost:8080/workouts/pages?query=" + query + "&page="+ (page - 1);
        ResponseEntity<WorkoutPage> workoutResponse = restTemplate.exchange(url,
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<WorkoutPage>(){});

        model.addAttribute("workouts", workoutResponse.getBody());

        return "workouts";
    }


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

    public String newWorkoutAdd( HttpServletRequest req,String name,ExerciseListWrapper exercises)
    {
        Workout workout = new Workout();
        workout.setName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(req));
        HttpEntity<Workout> request = new HttpEntity<>(workout, headers);

        ResponseEntity<Workout> workoutResponse = restTemplate.postForEntity("http://localhost:8080/workouts", request,Workout.class);
        if(workoutResponse.getStatusCode().is2xxSuccessful())
        {
            try
             {
             if(exercises.getExercises() != null)
             {
                 exercises.getExercises().forEach(exer -> 
                 {
                     ResponseEntity<WorkoutExercise> exerResponse = restTemplate.postForEntity("http://localhost:8080/workouts/"+ workoutResponse.getBody().getId() + "/exercises/" + exer.getId().getExerciseId() + "/"+exer.getSets()+"/"+ exer.getReps() +"/"+ exer.getWeight(), getCookieHeader(req),WorkoutExercise.class);
                     if(!exerResponse.getStatusCode().is2xxSuccessful())
                     {
                         throw new RuntimeException("Failed to add exercise with ID: " + exer.getExercise().getId());
                     }
                 });
             }

            return "redirect:/workouts";
             }
             catch(RuntimeException e)
            {
                 restTemplate.delete("http://localhost:8080/workouts/"+ workoutResponse.getBody().getId(),getCookieHeader(req));
                 return "redirect:/error";
             }
        }
        else
        {
            return "redirect:/error";
        }
    }

    public String editWorkoutPage(int id, Model model, HttpServletRequest req)
    {
        ResponseEntity<Workout> workoutResponse = restTemplate.exchange("http://localhost:8080/workouts/"+ id,
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<Workout>(){});
        Workout workout = workoutResponse.getBody();

        ResponseEntity<List<WorkoutExercise>> exercisesResponse = restTemplate.exchange("http://localhost:8080/exercises/workouts/"+ workout.getId()+"/full",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<WorkoutExercise>>(){});
        workout.setWorkoutExercises(exercisesResponse.getBody());

        model.addAttribute("workout", workoutResponse.getBody());

        ResponseEntity<List<ExerciseCategory>> exerciseCategoryResponse = restTemplate.exchange("http://localhost:8080/exercisecategories",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<ExerciseCategory>>(){});

        model.addAttribute("categories", exerciseCategoryResponse.getBody());

        ResponseEntity<List<Exercise>> allExercisesResponse = restTemplate.exchange("http://localhost:8080/exercises",
        HttpMethod.GET,
        getCookieHeader(req),
        new ParameterizedTypeReference<List<Exercise>>(){});

        model.addAttribute("allexercises", allExercisesResponse.getBody());


        return "edit-workout";
    }

    public String editWorkout(Workout workout, int id, HttpServletRequest req)
    {
        workout.getWorkoutExercises().removeIf(e -> e.getExercise() == null);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(req));
        HttpEntity<Workout> request = new HttpEntity<>(workout, headers);
        restTemplate.exchange("http://localhost:8080/workouts/"+ id, HttpMethod.PUT, request, Workout.class);
        return "redirect:/workouts";
    }


    public String deleteWorkout(int id, HttpServletRequest req)
    {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/workouts/"+ id, HttpMethod.DELETE,getCookieHeader(req), String.class);
        if(response.getStatusCode().is2xxSuccessful())
        {
            return "redirect:/workouts";
        }
        return "redirect:/error";
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
