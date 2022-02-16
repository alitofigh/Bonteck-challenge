package com.bonteck.challenge.bonteckchallenge.config;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.bonteck.challenge.bonteckchallenge.service.UserServices;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Ali Tofigh 2/16/2022 11:04 AM
 */

@Getter
@Configuration
public class servicesStatus {

 private static List<String> activeServicesList;

 private final ManagementServices managementServices;

 public servicesStatus(ManagementServices managementServices) {
  this.managementServices = managementServices;
 }

 @PostConstruct
 public void initData() {
  List<ServiceEntity> activeServices = managementServices.getServicesByStatus(true);
  activeServices.forEach(serviceEntity -> {
   if (serviceEntity.isStatus())
    activeServicesList.add(serviceEntity.getName());
  });
 }




}
