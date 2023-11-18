package de.tum.hackatum.hellofresh.service.authentication;

import de.tum.hackatum.hellofresh.port.in.*;
import de.tum.hackatum.hellofresh.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class AuthenticationService implements AuthenticateUseCase, RegisterUseCase {

    private final UserPort userPort;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResult register(AuthenticationRegisterCommand request) {
        UserDetails newUser = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .authorities(List.of())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();

        String jwtToken = jwtService.generateToken(newUser);
        String refreshToken = jwtService.generateRefreshToken(newUser);

        //todo what happens if the given username is already taken
        userPort.saveUserDetails(newUser);

        //todo should not create tokens
        return AuthenticationResult.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResult authenticate(AuthenticationCommand request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        //todo: jwt service?
        Optional<UserDetails> optional = userPort.loadUserByUsername(request.getUsername());

        if (optional.isEmpty())
            //todo maybe throw error
            return AuthenticationResult.builder()
                    .accessToken("")
                    .refreshToken("")
                    .build();

        UserDetails user = optional.get();

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResult.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}