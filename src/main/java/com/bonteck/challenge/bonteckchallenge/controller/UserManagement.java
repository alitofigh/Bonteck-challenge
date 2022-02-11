package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.google.gson.Gson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 1:36 PM
 */

@RestController
@RequestMapping("/management/users")
public class UserManagement {

    private final ManagementServices managementServices;

    public UserManagement(ManagementServices managementServices) {
        this.managementServices = managementServices;
    }

    @PostMapping("register/new-user")
    public String registerUser(Model model) {
        UserEntity user = new UserEntity();

        managementServices.save(user);
        return "";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2')")
    public String getUsers() {
        List<UserEntity> allUsers = managementServices.getAllUsers();
        return new Gson().toJson(allUsers);
    }
}
