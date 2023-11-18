package de.tum.hackatum.hellofresh.service.authentication.config;

import de.tum.hackatum.hellofresh.service.authentication.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class LogoutService implements LogoutHandler {

    private final AuthorizationHeaderValidator authorizationHeaderValidator;
    private final JwtService jwtService;

    /**
     * Performs the logout operation by revoking the JWT token, clearing the security context, and performing
     * additional validation.
     *
     * @param request        the HttpServletRequest object representing the incoming request
     * @param response       the HttpServletResponse object representing the outgoing response
     * @param authentication the Authentication object representing the current authentication
     * @throws IllegalArgumentException if the request or response is null
     */
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        if (!authorizationHeaderValidator.isTokenPresent(request))
            return;

        final String jwtToken = authorizationHeaderValidator.getToken(request);

        Optional<UserDetails> optional = jwtService.extractUserDetails(jwtToken);

        if (optional.isEmpty())
            return;

        UserDetails userDetails = optional.get();

        jwtService.revokeJwtToken(jwtToken, userDetails);
        SecurityContextHolder.clearContext();
    }

}