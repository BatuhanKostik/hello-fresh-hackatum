package de.tum.hackatum.hellofresh.service.authentication.config;

class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException() {
        super("Token not found in the request header.");
    }

}