package de.tum.hackatum.hellofresh.service.authentication.config;

import jakarta.servlet.http.HttpServletRequest;

public interface SecurityApiAccessibilityChecker {

    /**
     * Checks whether a given request is made against a public API. The check is started from the root, all child
     * paths are implicitly <b>NOT</b> allowed.
     *
     * @param request the HttpServletRequest object representing the incoming request
     * @return true if the request is against a public API, false otherwise
     */
    boolean isRequestAgainstPublicApi(HttpServletRequest request);


}
