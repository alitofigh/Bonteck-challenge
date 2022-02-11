package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
