package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 5:29 PM
 */

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);

    List<UserEntity> findAllByRoleId(Integer roleId);


}
