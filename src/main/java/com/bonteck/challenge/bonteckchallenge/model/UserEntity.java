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
    private String username;
    private String password;
    private Integer balance;
    private Integer roleId;
    private boolean nonLocked;
    private boolean enable;
}
