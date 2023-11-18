package de.tum.hackatum.hellofresh.service.authentication.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


import java.util.List;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String LOGOUT_URL = "/api/v1/auth/logout";

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final String[] publicAccessibleRequests;

    /**
     * Constructs a SecurityConfiguration instance with the specified dependencies and public accessible requests.
     *
     * @param jwtAuthFilter            the JwtAuthenticationFilter used for token-based authentication
     * @param authenticationProvider   the AuthenticationProvider used for authentication
     * @param logoutHandler            the LogoutHandler used for handling logout requests
     * @param publicAccessibleRequests the list of public accessible request paths
     */
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider,
            LogoutHandler logoutHandler,
            @Qualifier("publicAccessibleApiPaths") List<String> publicAccessibleRequests
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
        this.publicAccessibleRequests = publicAccessibleRequests.toArray(new String[0]);
    }

    /**
     * Configures the security filter chain and defines the security rules for the application.
     *
     * @param http the HttpSecurity object to be configured
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                                .requestMatchers(mvc.pattern("/api/v1/auth/register")).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        sessionManagement ->
                                sessionManagement
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout ->
                                logout
                                        .logoutUrl(LOGOUT_URL)
                                        .addLogoutHandler(logoutHandler)
                                        .logoutSuccessHandler((request, response, authentication) ->
                                                SecurityContextHolder.clearContext())
                );


        return http.build();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

}