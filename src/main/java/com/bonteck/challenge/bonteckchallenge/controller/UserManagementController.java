package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Ali Tofigh 2/10/2022 1:36 PM
 */

@RestController
@RequestMapping("/management/users")
public class UserManagementController {

    private final ManagementServices managementServices;

    public UserManagementController(ManagementServices managementServices) {
        this.managementServices = managementServices;
    }

    @PostMapping(value = "/add-new-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2', 'ROLE_ADMIN_1')")
    public String registerUser(@RequestBody UserParam user) {
        UserEntity userEntity = new UserEntity();
        /*userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setBalance(user.getBalance());
        userEntity.setRoleId(user.getRoleId());
        userEntity.setNonLocked(true);
        userEntity.setEnable(true);
        managementServices.save(userEntity);*/
        return "user added successfully";
    }

    @PostMapping("/change-role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2')")
    public String changeUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("user-name") int roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        userEntity.setRoleId(roleId);
        managementServices.merge(userEntity);
        return "done successfully";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2', 'ROLE_ADMIN_1')")
    public String getUsers() {
        List<UserEntity> allUsers = managementServices.getAllUsers();
        return new Gson().toJson(allUsers);
    }


}
