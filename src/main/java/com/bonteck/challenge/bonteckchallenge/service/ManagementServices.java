package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.repository.ServiceRepository;
import com.bonteck.challenge.bonteckchallenge.repository.UserServicesRepository;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ServiceRepository serviceRepository;
    private final UserServicesRepository userServicesRepository;

    @Autowired
    public ManagementServices(
            UserRepository userRepository,
            ServiceRepository serviceRepository,
            UserServicesRepository userServicesRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.userServicesRepository = userServicesRepository;
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


    /*public void getActiveServices() {
        List<ServiceEntity> activeServices = serviceRepository.findAllByActive(true);

    }*/

    /*public int changeServiceStatus(long serviceId, boolean status) {
        return serviceRepository.changeServiceStatus(serviceId, status);
    }*/

    public void giveUserAccessToUser(String username, long serviceId) {
        UserServicesEntity userServicesEntity = new UserServicesEntity();
        userServicesEntity.setUser(userRepository.findUserEntityByUsername(username));
        userServicesEntity.setService(serviceRepository.findServiceEntityById(serviceId));
        userServicesEntity.setActive(true);
        userServicesEntity.setCount(0);
        userServicesRepository.save(userServicesEntity);
    }

    public void changeUserServiceStatus(String username, long serviceId) {
        UserServicesEntity userServicesEntity =
                userServicesRepository.findByUserAndService(
                        userRepository.findUserEntityByUsername(username),
                        serviceRepository.findServiceEntityById(serviceId)
                );
        userServicesEntity.setActive(true);
        userServicesRepository.save(userServicesEntity);
    }
}
