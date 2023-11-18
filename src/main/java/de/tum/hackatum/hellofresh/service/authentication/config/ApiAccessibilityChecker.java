package de.tum.hackatum.hellofresh.service.authentication.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ApiAccessibilityChecker implements SecurityApiAccessibilityChecker {
    private final List<String> accessibleApiPaths;

    /**
     * Constructs an {@code ApiAccessibilityChecker} instance with the specified list of accessible API paths.
     *
     * @param accessibleApiPaths the list of accessible API paths
     */
    public ApiAccessibilityChecker(
            @Qualifier("publicAccessibleApiPaths") List<String> accessibleApiPaths
    ) {
        this.accessibleApiPaths = accessibleApiPaths;
    }

    /**
     * Checks whether the given request is made against a public API by comparing the request path with the
     * accessible API paths.
     *
     * @param request the HttpServletRequest object representing the incoming request
     * @return {@code true} if the request is against a public API, {@code false} otherwise
     */
    @Override
    public boolean isRequestAgainstPublicApi(HttpServletRequest request) {
        String path = request.getServletPath();
        return accessibleApiPaths.stream().anyMatch(path::equals);
    }

}