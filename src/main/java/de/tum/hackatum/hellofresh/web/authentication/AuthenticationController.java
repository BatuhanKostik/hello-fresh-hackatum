package de.tum.hackatum.hellofresh.web.authentication;

import de.tum.hackatum.hellofresh.port.in.AuthenticationRegisterCommand;
import de.tum.hackatum.hellofresh.port.in.AuthenticationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.tum.hackatum.hellofresh.port.in.RegisterUseCase;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthenticationController {

    private final RegisterUseCase registerUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResult> register(@RequestBody RegisterRequest request) {
        AuthenticationRegisterCommand command = new AuthenticationRegisterCommand(
                  request.username(),
              request.password()
        );

        AuthenticationResult authenticate = registerUseCase.register(command);

        return ResponseEntity.ok(authenticate);
    }



}