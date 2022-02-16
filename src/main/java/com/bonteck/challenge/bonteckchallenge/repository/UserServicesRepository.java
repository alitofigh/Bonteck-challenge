package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ali Tofigh 2/14/2022 8:51 PM
 */

@Repository
public interface UserServicesRepository extends CrudRepository<UserServicesEntity, Long> {

    List<UserServicesEntity> findAllByUser(UserEntity user);
    //List<UserServicesEntity> findAllByActive(Boolean status);
    UserServicesEntity findByUserAndService(UserEntity user, ServiceEntity service);

    List<UserServicesEntity> findAllByUserAndStatus(UserEntity user, boolean status);
}
