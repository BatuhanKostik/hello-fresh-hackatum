package de.tum.hackatum.hellofresh.persistence.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_detail_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsEntity {

    @Id
    @GeneratedValue
    private long id;
    private String password;
    @Column(unique = true)
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

}