package de.tum.hackatum.hellofresh.service.authentication.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ApiAccessibilityChecker implements SecurityApiAccessibilityChecker {
    private final List<String> accessibleApiPaths;

    public ApiAccessibilityChecker(
            @Qualifier("publicAccessibleApiPaths") List<String> accessibleApiPaths
    ) {
        this.accessibleApiPaths = accessibleApiPaths;
    }

    @Override
    public boolean isRequestAgainstPublicApi(HttpServletRequest request) {
        String path = request.getServletPath();
        return accessibleApiPaths.stream().anyMatch(path::equals);
    }

}