package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import com.bonteck.challenge.bonteckchallenge.security.ApplicationUserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<UserProperties> getAllUsers() {
        List<UserEntity> users = (List<UserEntity>)userRepository.findAll();
        List<UserProperties> userList = new ArrayList<>();
        users.forEach(userEntity -> {
            UserProperties user = new UserProperties();
            user.setName(userEntity.getName());
            user.setUsername(userEntity.getUsername());
            user.setBalance(userEntity.getBalance());
            Set<RoleEntity> roles = userEntity.getRoles();
            StringBuilder userRoles = new StringBuilder();
            roles.forEach(roleEntity -> {
                userRoles.append(roleEntity.getRoleName()).append(", ");
            });
            user.setRole(userRoles.toString());
            user.setEnable(userEntity.isEnable());
            user.setLocked(userEntity.isNonLocked());
            userList.add(user);
        });
        return userList;
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    /*public List<UserEntity> getAllUsersInRole(ApplicationUserRole role) {
        return userRepository.findAllById(role.getRoleId());
    }*/
}
