package de.tum.hackatum.hellofresh.port.out;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenPort {

    boolean isTokenExcluded(UserDetails userDetails, String token);

    void revokeToken(UserDetails user, String jwtToken);

}
