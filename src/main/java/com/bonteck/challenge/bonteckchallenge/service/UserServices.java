package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.response.UserStatusResponse;
import com.bonteck.challenge.bonteckchallenge.security.ApplicationUserRole;
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
        UserStatusResponse userStatusResponse = new UserStatusResponse();
        userStatusResponse.setUsername(userEntity.getUsername());
        userStatusResponse.setRole(getRole(userEntity.getRoleId()).name());
        userStatusResponse.setBalance(userEntity.getBalance());
        userStatusResponse.setEnable(userEntity.isEnable());
        userStatusResponse.setLocked(userEntity.isNonLocked());
        return userStatusResponse.prepareResult(userStatusResponse);
    }
}
