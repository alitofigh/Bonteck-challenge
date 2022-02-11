package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static com.bonteck.challenge.bonteckchallenge.security.ApplicationUserRole.getRole;

/**
 * @author Ali Tofigh 2/10/2022 11:27 AM
 */

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public String getUserStatus(String username) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        UserProperties userProperties = new UserProperties();
        userProperties.setUsername(userEntity.getUsername());
        userProperties.setRole(getRole(userEntity.getRoleId()).name());
        userProperties.setBalance(userEntity.getBalance());
        userProperties.setEnable(userEntity.isEnable());
        userProperties.setLocked(userEntity.isNonLocked());
        return userProperties.prepareResult(userProperties);
    }
}
