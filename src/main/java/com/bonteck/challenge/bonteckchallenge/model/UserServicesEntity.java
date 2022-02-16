package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ali Tofigh 2/10/2022 3:00 PM
 */

@Getter
@Setter
@Entity(name = "USER_SERVICE")
public class UserServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private int count;
    private int max;
    private boolean status;
}
