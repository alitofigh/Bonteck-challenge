package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ali Tofigh 2/10/2022 3:00 PM
 */

@Getter
@Setter
@Entity
public class UserActions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serviceId;
    private int userId;
}
