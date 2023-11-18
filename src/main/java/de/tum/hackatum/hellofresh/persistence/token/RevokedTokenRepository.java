package de.tum.hackatum.hellofresh.persistence.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevokedTokenRepository extends JpaRepository<RevokedTokenEntity, Long> {

    Optional<RevokedTokenEntity>
    getByJwtTokenAndUserDetailsEntityUsername(String jwtToken, String userDetailsEntityUsername);

}