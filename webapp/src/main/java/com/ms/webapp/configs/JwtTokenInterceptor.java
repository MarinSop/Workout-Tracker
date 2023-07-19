package com.ms.webapp.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check for the presence of the JWT token cookie
        boolean tokenPresent = isJwtTokenPresent(request);

        // If the token is not present, redirect to the login page
        if (!tokenPresent) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    private boolean isJwtTokenPresent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt_token".equals(cookie.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
