package com.gradlic.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(updatable = false, unique = true, nullable = false)
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;

    private Integer loginAttempts;
    private LocalDateTime lastLogin;

    private String phone;
    private String bio;

    private String imageUrl;;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    // private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean mfa;

    @JsonIgnore
    private String qrCodeSecret;
    @Column(columnDefinition = "text")
    private String qrCodeImageUri;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserOrganisationRoleEntity> organisations; // multi-tenant relationship

    // private String roles;



    // ROLE_ADMIN user:update, user:delete
    // ROLE_USER user:read
    // ROLE_GUEST

}
