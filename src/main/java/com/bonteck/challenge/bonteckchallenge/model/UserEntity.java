package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Ali Tofigh 2/10/2022 11:50 AM
 */

@Getter
@Setter
@Entity(name = "USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private int balance;
    private boolean nonLocked;
    private boolean enable;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<RoleEntity> roles;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_SERVICE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="service_id"))
    private Set<ServiceEntity> services;
}
