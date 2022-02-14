package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.bonteck.challenge.bonteckchallenge.service.RoleServices;
import com.google.gson.Gson;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/
import org.springframework.beans.factory.annotation.Autowired;
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
//@Api(value = "Verify Token Service", description = "Verify VASCO and PSP Token")
public class UserManagementController {

    private final ManagementServices managementServices;
    private final RoleServices roleServices;

    @Autowired
    public UserManagementController(ManagementServices managementServices, RoleServices roleServices) {
        this.managementServices = managementServices;
        this.roleServices = roleServices;
    }

/*    @ApiOperation(value = "Verify Token Service", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )*/
    @PostMapping(value = "/add-new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('management:register')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LEVEL_8')")
    public String addUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") long roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().add(roleEntity);
        managementServices.merge(userEntity);
        return "role was added to user successfully";
    }

    @PostMapping("/remove-role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LEVEL_8')")
    public String removeUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") long roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().remove(roleEntity);
        managementServices.merge(userEntity);
        return "role was deleted successfully";
    }

    @GetMapping("/list-users")
    @PreAuthorize("hasAuthority('management:list-users')")
    public String getUsers() {
        List<UserProperties> allUsers = managementServices.getAllUsers();
        return new Gson().toJson(allUsers);
    }

    /*@PostMapping("/change-service-status")
    @PreAuthorize("hasAuthority('management:change-service-status')")
    public String changeServiceStatus(
            @RequestParam("service-id") long serviceId,
            @RequestParam("status") boolean status) {
        int res = managementServices.changeServiceStatus(serviceId, status);
        return "done successfully";
    }*/

    @PostMapping("/service-access-to-user")
    @PreAuthorize("hasAuthority('management:service-access-to-user')")
    public String giveServiceAccessToUser(
            @RequestParam("user-name") String username,
            @RequestParam("service-id") long serviceId) {
        managementServices.giveUserAccessToUser(username, serviceId);
        return "done successfully";
    }

    @PostMapping("/change-user-service-status")
    public String changeUserServiceStatus(
            @RequestParam("user-name") String username,
            @RequestParam("service-id") long serviceId) {
        managementServices.changeUserServiceStatus(username, serviceId);
        return "done successfully";
    }
}
