package com.bonteck.challenge.bonteckchallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
 private int max;
 private Date activationDate;
}
