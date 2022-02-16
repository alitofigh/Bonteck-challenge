package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 5:29 PM
 */

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);

    //List<UserEntity> findAllById(Integer roleId);




}
