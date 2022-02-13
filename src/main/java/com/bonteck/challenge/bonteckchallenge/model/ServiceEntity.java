package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Ali Tofigh 2/10/2022 2:38 PM
 */

@Getter
@Setter
@Entity(name = "SERVICE")
public class ServiceEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private Integer cost;
 private boolean isActive;
 private Date activationDate;
 @ManyToMany(mappedBy = "services")
 private Set<UserEntity> users;
}
