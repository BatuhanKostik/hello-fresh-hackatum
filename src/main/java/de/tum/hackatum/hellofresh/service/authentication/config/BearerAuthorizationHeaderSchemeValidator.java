package de.tum.hackatum.hellofresh.service.authentication.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
class BearerAuthorizationHeaderSchemeValidator implements AuthorizationHeaderValidator {

    private static final String BEARER_AUTHENTICATION_SCHEMA = "Bearer ";
    private static final int BEARER_AUTHENTICATION_SCHEMA_OFFSET = BEARER_AUTHENTICATION_SCHEMA.length();

    @Override
    public boolean isTokenPresent(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !(authHeader == null || !authHeader.startsWith(BEARER_AUTHENTICATION_SCHEMA));
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(BEARER_AUTHENTICATION_SCHEMA))
            throw new TokenNotFoundException();

        return header.substring(BEARER_AUTHENTICATION_SCHEMA_OFFSET);
    }

}