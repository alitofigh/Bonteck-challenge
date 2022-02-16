package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import com.bonteck.challenge.bonteckchallenge.service.UserServices;
import com.google.gson.Gson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 11:38 AM
 */

@RestController
@RequestMapping("/users")
public class InquiryController {

    private final UserServices userServices;

    public InquiryController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/get-user-status")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String getUserStatus(@RequestParam("user-name") String username) {
        return userServices.getUserStatus(username);
    }

    @GetMapping("/get-user-allowed-services")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String getUserAllowedServices(@RequestParam("user-name") String username) {
        List<UserServicesEntity> userServices = this.userServices.getUserServices(username);
        return new Gson().toJson(userServices);
    }

    @GetMapping("/get-user-active-services")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String getUserActiveServices(@RequestParam("user-name") String username) {
        List<UserServicesEntity> userActiveServices = userServices.getUserActiveServices(username, true);
        return new Gson().toJson(userActiveServices);
    }

    @GetMapping("/show-used-services")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String showUsedServices(@RequestParam("user-name") String username) {
        List<UserServicesEntity> userUsedServices = userServices.showUsedService(username);
        return new Gson().toJson(userUsedServices);
    }
}
