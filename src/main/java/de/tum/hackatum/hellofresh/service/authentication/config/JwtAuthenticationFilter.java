package de.tum.hackatum.hellofresh.service.authentication.config;

import de.tum.hackatum.hellofresh.service.authentication.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final SecurityApiAccessibilityChecker accessibilityChecker;
    private final AuthorizationHeaderValidator authorizationHeaderValidator;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        doFilterInternal(request);
        filterChain.doFilter(request, response);
    }

    private void doFilterInternal(HttpServletRequest request) {
        if (accessibilityChecker.isRequestAgainstPublicApi(request) ||
                !authorizationHeaderValidator.isTokenPresent(request) || isUserAlreadyAuthenticated())
            return;

        final String jwt = authorizationHeaderValidator.getToken(request);
        Optional<UserDetails> optional = jwtService.extractUserDetails(jwt);

        if (optional.isEmpty())
            return;

        UserDetails userDetails = optional.get();

        if (!jwtService.isTokenExpired(jwt) && !jwtService.isTokenExcluded(jwt, userDetails))
            enableUserAsAuthenticated(userDetails, request);
    }

    private void enableUserAsAuthenticated(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private boolean isUserAlreadyAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() == null);
    }

}