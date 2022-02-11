package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.service.UserServices;
import com.google.gson.Gson;
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
    public String getUserStatus(@RequestParam("user-name") String username) {
        return userServices.getUserStatus(username);
    }

    @GetMapping("/get-user-allowed-services")
    public String getUserAllowedServices(@RequestParam("user-name") String username) {
        return "";
    }

    @GetMapping("/user-active-services")
    public String getUserActiveServices(@RequestParam("user-name") String username) {
        return "";
    }


}
