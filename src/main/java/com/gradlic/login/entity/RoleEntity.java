package com.gradlic.login.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gradlic.login.enumaration.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleEntity extends BaseEntity {
    private String name;
    private Authority authorities; // user:read
}
