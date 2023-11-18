package de.tum.hackatum.hellofresh.persistence.user;

import de.tum.hackatum.hellofresh.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDetailsPersistenceAdapter implements UserPort {

    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsMapper userDetailsMapper;

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        UserDetailsEntity userDetailsEntity = userDetailsMapper.mapToUserDetailsEntity(userDetails);

        userDetailsRepository.save(userDetailsEntity);
    }

    @Override
    public Optional<UserDetails> loadUserByUsername(String username) {
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(username);

        return userDetailsMapper.mapToUserDetails(byUsername);
    }

    @Override
    public boolean existUsername(String username) {
        return userDetailsRepository.findByUsername(username).isPresent();
    }


}