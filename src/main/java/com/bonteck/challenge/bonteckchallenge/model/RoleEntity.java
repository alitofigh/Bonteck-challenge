package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Ali Tofigh 2/10/2022 12:01 PM
 */

@Getter
@Setter
@Entity(name = "ROLE")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roleId;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
