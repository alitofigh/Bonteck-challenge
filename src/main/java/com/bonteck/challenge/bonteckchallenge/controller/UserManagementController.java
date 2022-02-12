package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.bonteck.challenge.bonteckchallenge.service.RoleServices;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 1:36 PM
 */

@RestController
@RequestMapping("/management/users")
public class UserManagementController {

    private final ManagementServices managementServices;
    private final RoleServices roleServices;

    public UserManagementController(ManagementServices managementServices, RoleServices roleServices) {
        this.managementServices = managementServices;
        this.roleServices = roleServices;
    }

    @PostMapping(value = "/add-new-user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2', 'ROLE_ADMIN_1')")
    public String registerUser(@Validated @RequestBody UserParam user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setBalance(user.getBalance());
        userEntity.setNonLocked(true);
        userEntity.setEnable(true);
        managementServices.save(userEntity);
        return "user added successfully";
    }

    @PostMapping("/add-role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2')")
    public String addUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") int roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().add(roleEntity);
        managementServices.merge(userEntity);
        return "role was added to user successfully";
    }

    @PostMapping("/remove-role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2')")
    public String removeUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") int roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().remove(roleEntity);
        managementServices.merge(userEntity);
        return "role was deleted successfully";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_2', 'ROLE_ADMIN_1')")
    public String getUsers() {
        List<UserEntity> allUsers = managementServices.getAllUsers();
        return new Gson().toJson(allUsers);
    }


}
