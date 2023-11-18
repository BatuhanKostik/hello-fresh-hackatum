package de.tum.hackatum.hellofresh.persistence.token;

import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "revoked_token_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevokedTokenEntity {

    @Id
    @GeneratedValue
    private long id;
    private String jwtToken;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDetailsEntity userDetailsEntity;

}