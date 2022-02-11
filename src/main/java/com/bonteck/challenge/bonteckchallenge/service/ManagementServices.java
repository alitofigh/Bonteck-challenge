package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.security.ApplicationUserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 11:28 AM
 */

@Service
public class ManagementServices {

    private final UserRepository userRepository;

    public ManagementServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void merge(UserEntity userEntity) {
        this.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>)userRepository.findAll();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    public List<UserEntity> getAllUsersInRole(ApplicationUserRole role) {
        return userRepository.findAllByRoleId(role.getRoleId());
    }
}
