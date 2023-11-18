package de.tum.hackatum.hellofresh.port.in;


public record AuthenticationRegisterCommand(String username, String password) {
}