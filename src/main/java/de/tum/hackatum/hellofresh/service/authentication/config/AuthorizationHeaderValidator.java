package de.tum.hackatum.hellofresh.service.authentication.config;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderValidator {

    boolean isTokenPresent(HttpServletRequest request);

    String getToken(HttpServletRequest request);

}