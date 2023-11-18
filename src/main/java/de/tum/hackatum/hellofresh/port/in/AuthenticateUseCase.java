package de.tum.hackatum.hellofresh.port.in;

public interface AuthenticateUseCase {

    AuthenticationResult authenticate(AuthenticationCommand request);

}