package de.tum.hackatum.hellofresh.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {

    Optional<UserDetailsEntity> findByUsername(String username);

}