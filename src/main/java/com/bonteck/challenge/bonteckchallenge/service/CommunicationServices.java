package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.repository.ServiceRepository;

/**
 * @author Ali Tofigh 2/13/2022 10:57 PM
 */

public class CommunicationServices {

    private final ServiceRepository serviceRepository;

    public CommunicationServices(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void useService(String username, int serviceId) {
    }
}
