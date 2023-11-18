package de.tum.hackatum.hellofresh.service.authentication.config;

import de.tum.hackatum.hellofresh.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final UserPort loadUserDetailsPort;

    @Bean
    @Qualifier("publicAccessibleApiPaths")
    public List<String> publicAccessibleApiPaths() {
        return List.of(
                "/api/v1/auth/register",
                "/*"
           //     "/api/v1/auth/close"
        );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> loadUserDetailsPort.loadUserByUsername(username)
                .orElseThrow();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}