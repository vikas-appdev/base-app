package com.gradlic.login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organisations")
public class OrganisationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String address;

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    private List<UserOrganisationRoleEntity> users; // users assigned with roles



}
