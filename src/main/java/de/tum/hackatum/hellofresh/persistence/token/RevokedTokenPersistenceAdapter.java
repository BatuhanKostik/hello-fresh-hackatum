package de.tum.hackatum.hellofresh.persistence.token;

import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsRepository;
import de.tum.hackatum.hellofresh.port.out.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RevokedTokenPersistenceAdapter implements TokenPort {

    private final RevokedTokenRepository revokedTokenRepository;
    private final RevokedTokenMapper revokedTokenMapper;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public boolean isTokenExcluded(UserDetails userDetails, String token) {
        Optional<RevokedTokenEntity> revokedToken =
                revokedTokenRepository.getByJwtTokenAndUserDetailsEntityUsername(token, userDetails.getUsername());

        return revokedToken.isPresent();
    }

    @Override
    public void revokeToken(UserDetails user, String jwtToken) {
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(user.getUsername());

        if (byUsername.isEmpty())
            return;

        UserDetailsEntity userDetailsEntity = byUsername.get();

        RevokedTokenEntity revokedTokenEntity = revokedTokenMapper
                .mapToRevokedTokenEntity(userDetailsEntity, jwtToken);

        revokedTokenRepository.save(revokedTokenEntity);
    }

}