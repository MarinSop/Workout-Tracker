package com.ms.webapp.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class StatisticsService {

    @Autowired
    private RestTemplate restTemplate;

    public String statisticsPage(Model model, HttpServletRequest req)
    {
        LocalDate date = LocalDate.now();
        ResponseEntity<Integer> monthResponse = restTemplate.exchange(
        "http://localhost:8080/logs/month/"+ date.getMonthValue() + "/exercises", HttpMethod.GET, getCookieHeader(req), Integer.class);
        model.addAttribute("daysInMonth", monthResponse.getStatusCode().is2xxSuccessful() ? monthResponse.getBody() : "Failed to retrive.");

        ResponseEntity<Integer> yearResponse = restTemplate.exchange(
        "http://localhost:8080/logs/year/"+ date.getYear() + "/exercises", HttpMethod.GET, getCookieHeader(req), Integer.class);
        model.addAttribute("daysInYear", yearResponse.getStatusCode().is2xxSuccessful() ? yearResponse.getBody() : "Failed to retrive.");

        ResponseEntity<Integer> allExercisesResponse = restTemplate.exchange(
        "http://localhost:8080/logs/all-exercises-done", HttpMethod.GET, getCookieHeader(req), Integer.class);
        model.addAttribute("allExercisesDone", allExercisesResponse.getStatusCode().is2xxSuccessful() ? allExercisesResponse.getBody() : "Failed to retrive.");

        return "statistics";
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
