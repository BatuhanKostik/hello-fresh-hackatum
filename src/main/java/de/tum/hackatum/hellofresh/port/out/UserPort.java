package de.tum.hackatum.hellofresh.port.out;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserPort {

    void saveUserDetails(UserDetails userDetails);

    Optional<UserDetails> loadUserByUsername(String username);

    boolean existUsername(String username);

}
