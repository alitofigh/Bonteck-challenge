package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author Ali Tofigh 2/12/2022 4:08 PM
 */

@Service
public class RoleServices {

    private final RoleRepository roleRepository;

    public RoleServices(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRole(Long roleId) {
        return roleRepository.findRoleEntityById(roleId);
    }
}
