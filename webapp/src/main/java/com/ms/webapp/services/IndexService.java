package com.ms.webapp.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.ms.webapp.models.Log;
import com.ms.webapp.models.LogExercise;
import com.ms.webapp.models.LogExerciseId;
import com.ms.webapp.models.LogExercisePage;
import com.ms.webapp.models.Workout;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class IndexService {

    @Autowired
    private RestTemplate restTemplate;

    public String index(Model model, HttpServletRequest req, LocalDate date, int page) {
        if (date == null) {
            date = LocalDate.now();
        }
        model.addAttribute("date", date);
        model.addAttribute("dateFormated", DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date));
        model.addAttribute("today", date.isEqual(LocalDate.now()));
        model.addAttribute("yesterday", date.isEqual(LocalDate.now().minusDays(1)));
        model.addAttribute("tommorow", date.isEqual(LocalDate.now().plusDays(1)));
        model.addAttribute("dateDec", date.minusDays(1));
        model.addAttribute("dateInc", date.plusDays(1));
        ResponseEntity<List<Workout>> allWorkoutsResponse = restTemplate.exchange("http://localhost:8080/workouts",
                HttpMethod.GET,
                getCookieHeader(req),
                new ParameterizedTypeReference<List<Workout>>() {
                });
        model.addAttribute("allWorkouts", allWorkoutsResponse.getBody());

        ResponseEntity<List<ExerciseCategory>> exerciseCategoryResponse = restTemplate.exchange(
                "http://localhost:8080/exercisecategories",
                HttpMethod.GET,
                getCookieHeader(req),
                new ParameterizedTypeReference<List<ExerciseCategory>>() {
                });

        model.addAttribute("exerciseCategories", exerciseCategoryResponse.getBody());

        ResponseEntity<List<Exercise>> allExercisesResponse = restTemplate.exchange("http://localhost:8080/exercises",
                HttpMethod.GET,
                getCookieHeader(req),
                new ParameterizedTypeReference<List<Exercise>>() {
                });

        model.addAttribute("allExercises", allExercisesResponse.getBody());

        try {
            ResponseEntity<LogExercisePage> exercisePageResponse = restTemplate.exchange(
                    "http://localhost:8080/logs/" + date + "/exercises/pages?page=" + (page - 1),
                    HttpMethod.GET,
                    getCookieHeader(req),
                    new ParameterizedTypeReference<LogExercisePage>() {
                    });
            model.addAttribute("logExercisePage", exercisePageResponse.getBody());
        } catch (Exception e) {
            model.addAttribute("logExercisePage", new LogExercisePage());
        }

        return "home";
    }

    public String logWorkout(String date, int page, int workoutId, HttpServletRequest req) {
        ResponseEntity<Log> loggedWorkoutsResponse = restTemplate.exchange(
                "http://localhost:8080/logs/" + date + "/workouts/" + workoutId,
                HttpMethod.POST,
                getCookieHeader(req),
                new ParameterizedTypeReference<Log>() {
                });

        if (loggedWorkoutsResponse.getStatusCode().is2xxSuccessful())
            return "redirect:/home?date=" + date + "&page=" + page;
        return "redirect:/error";
    }

    public String logExercise(String date,int page,int exerciseId,int reps,int sets,int weight,HttpServletRequest req) {
        LogExercise logExercise = new LogExercise();
        logExercise.setId(new LogExerciseId());
        logExercise.getId().setExerciseId(exerciseId);
        logExercise.setReps(reps);
        logExercise.setSets(sets);
        logExercise.setWeight(weight);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(req));
        HttpEntity<LogExercise> request = new HttpEntity<>(logExercise, headers);

        ResponseEntity<Log> logExerciseResponse = restTemplate
                .postForEntity("http://localhost:8080/logs/" + date + "/exercises", request, Log.class);
        if (logExerciseResponse.getStatusCode().is2xxSuccessful()) {
            return "redirect:/home?date=" + date + "&page=" + page;
        }

        return "redirect:/error";
    }

    public String exerciseDelete(int logId,String date,int page,int exerciseId, HttpServletRequest req) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/logs/exercises?logId=" + logId + "&exerciseId=" + exerciseId, HttpMethod.DELETE,
                getCookieHeader(req), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/home?date=" + date + "&page=" + page;
        }
        return "redirect:/error";
    }

    HttpEntity<String> getCookieHeader(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String token = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("jwt_token"))
                .map(Cookie::getValue)
                .findFirst().get();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return requestEntity;
    }

    String getToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String token = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("jwt_token"))
                .map(Cookie::getValue)
                .findFirst().get();
        return token;
    }
}
