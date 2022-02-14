package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.repository.ServiceRepository;
import com.bonteck.challenge.bonteckchallenge.repository.UserServicesRepository;
import com.bonteck.challenge.bonteckchallenge.repository.UserRepository;
import com.bonteck.challenge.bonteckchallenge.response.ServiceStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali Tofigh 2/13/2022 10:57 PM
 */

@Service
public class CommunicationServices {

    private final ServiceRepository ServiceRepository;
    private final UserRepository userRepository;
    private final UserServicesRepository userServicesRepository;

    public CommunicationServices(ServiceRepository ServiceRepository, UserRepository userRepository, UserServicesRepository userServicesRepository) {
        this.ServiceRepository = ServiceRepository;
        this.userRepository = userRepository;
        this.userServicesRepository = userServicesRepository;
    }

    public void save(String username, int serviceId) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        ServiceEntity ServiceEntity = ServiceRepository.findServiceEntityById(Long.parseLong("" + serviceId));
        UserServicesEntity userServicesEntity = new UserServicesEntity();
        userServicesEntity.setUser(userEntity);
        userServicesEntity.setService(ServiceEntity);
        userServicesEntity.setCount(0);
        userServicesRepository.save(userServicesEntity);
    }

    public List<ServiceStatus> getUserServices(String username) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        List<UserServicesEntity> userServices = userServicesRepository.findAllByUser(userEntity);
        List<ServiceStatus> serviceStatusList = new ArrayList<>();
        userServices.forEach(userActivity -> {
            ServiceStatus serviceStatus = new ServiceStatus();
            serviceStatus.setName(userActivity.getService().getName());
            serviceStatus.setCount(userActivity.getCount());
            //serviceStatus.setStatus(userActivity.isActive());
            serviceStatusList.add(serviceStatus);
        });
        return serviceStatusList;
    }

    /*public void getActiveServices() {
        List<UserServicesEntity> userServices = userServicesRepository.findAllByActive(true);
    }*/
}
