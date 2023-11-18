package de.tum.hackatum.hellofresh.persistence.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsMapper {

    /**
     * Maps an {@code Optional<UserDetailsEntity>} to an {@code Optional<UserDetails>}.
     *
     * @param optional the optional UserDetailsEntity object
     * @return an  {@code Optional<UserDetails>} object if the input is present, otherwise an empty Optional
     */
    public Optional<UserDetails> mapToUserDetails(Optional<UserDetailsEntity> optional) {
        if (optional.isEmpty())
            return Optional.empty();

        UserDetailsEntity entity = optional.get();

        return Optional.of(new User(
                entity.getUsername(),
                entity.getPassword(),
                entity.isEnabled(),
                entity.isAccountNonExpired(),
                entity.isCredentialsNonExpired(),
                entity.isAccountNonLocked(),
                List.of()
        ));
    }

    /**
     * Maps a {@code UserDetails} object to a {@code UserDetailsEntity} object.
     *
     * @param userDetails the {@code UserDetails} object to be mapped
     * @return the mapped {@code UserDetailsEntity} object
     */
    public UserDetailsEntity mapToUserDetailsEntity(UserDetails userDetails) {
        return UserDetailsEntity.builder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .isEnabled(userDetails.isEnabled())
                .isAccountNonExpired(userDetails.isAccountNonExpired())
                .isCredentialsNonExpired(userDetails.isCredentialsNonExpired())
                .isAccountNonLocked(userDetails.isAccountNonLocked())
                .build();
    }

}