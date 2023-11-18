package de.tum.hackatum.hellofresh.persistence.token;

import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import org.springframework.stereotype.Component;

@Component
public class RevokedTokenMapper {

    public RevokedTokenEntity mapToRevokedTokenEntity(UserDetailsEntity user, String jwtToken) {
        return RevokedTokenEntity.builder()
                .jwtToken(jwtToken)
                .userDetailsEntity(user)
                .build();
    }

}
