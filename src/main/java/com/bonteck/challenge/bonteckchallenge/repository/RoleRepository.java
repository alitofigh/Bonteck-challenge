package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ali Tofigh 2/10/2022 5:48 PM
 */
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    RoleEntity findRoleEntityByRoleId(int roleId);
}
