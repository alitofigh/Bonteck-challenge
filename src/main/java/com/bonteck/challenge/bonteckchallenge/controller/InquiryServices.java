package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.service.UserServices;
import com.google.gson.Gson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali Tofigh 2/10/2022 11:38 AM
 */

@RestController
@RequestMapping("/users")
public class InquiryServices {

    private final UserServices userServices;

    public InquiryServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/get-user-status")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String getUserStatus(@RequestParam("user-name") String username) {
        return userServices.getUserStatus(username);
    }

    @GetMapping("/get-user-allowed-services")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_2')")
    public String getUserAllowedServices(@RequestParam("user-name") String username) {
        return "";
    }

    @GetMapping("/user-active-services")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_1')")
    public String getUserActiveServices(@RequestParam("user-name") String username) {
        return "";
    }


}
