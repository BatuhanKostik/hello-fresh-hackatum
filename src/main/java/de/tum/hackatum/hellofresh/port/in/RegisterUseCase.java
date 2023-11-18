package de.tum.hackatum.hellofresh.port.in;

public interface RegisterUseCase {

    AuthenticationResult register(AuthenticationRegisterCommand request);

}