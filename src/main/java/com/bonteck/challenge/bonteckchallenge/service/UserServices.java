package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.repository.UserServicesRepository;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 11:27 AM
 */

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final UserServicesRepository userServicesRepository;

    public UserServices(UserRepository userRepository, UserServicesRepository userServicesRepository) {
        this.userRepository = userRepository;
        this.userServicesRepository = userServicesRepository;
    }

    @SneakyThrows
    public String getUserStatus(String username) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        UserProperties userProperties = new UserProperties();
        userProperties.setUsername(userEntity.getUsername());
        StringBuilder roles = new StringBuilder();
        for (RoleEntity role : userEntity.getRoles()) {
            roles.append(role.getRoleName()).append(", ");
        }
        userProperties.setRole(roles.toString());
        userProperties.setBalance(userEntity.getBalance());
        userProperties.setEnable(userEntity.isEnable());
        userProperties.setLocked(userEntity.isNonLocked());
        return userProperties.prepareResult(userProperties);
    }

    public List<UserServicesEntity> getUserActiveServices(String username, boolean status) {
        return userServicesRepository.findAllByUserAndStatus(userRepository.findUserEntityByUsername(username), status);
    }

    public List<UserServicesEntity> getUserServices(String username) {
        return userServicesRepository.findAllByUser(userRepository.findUserEntityByUsername(username));
    }

    public List<UserServicesEntity> showUsedService(String username) {
        return userServicesRepository.findAllByUser(userRepository.findUserEntityByUsername(username));
    }
}
