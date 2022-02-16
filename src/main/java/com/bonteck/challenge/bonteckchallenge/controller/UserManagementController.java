package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.RoleEntity;
import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.request.UserParam;
import com.bonteck.challenge.bonteckchallenge.response.UserProperties;
import com.bonteck.challenge.bonteckchallenge.service.CommunicationServices;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import com.bonteck.challenge.bonteckchallenge.service.RoleServices;
import com.bonteck.challenge.bonteckchallenge.service.UserServices;
import com.google.gson.Gson;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 1:36 PM
 */

@Api(value = "User management", description = "These services are to add user, grant user, activate services, and ...")
@RestController
@RequestMapping("/management/users")
//@Api(value = "Verify Token Service", description = "Verify VASCO and PSP Token")
public class UserManagementController {

    private final ManagementServices managementServices;
    private final RoleServices roleServices;
    private final UserServices userServices;
    private final CommunicationServices communicationServices;

    @Autowired
    public UserManagementController(ManagementServices managementServices, RoleServices roleServices, UserServices userServices, CommunicationServices communicationServices) {
        this.managementServices = managementServices;
        this.roleServices = roleServices;
        this.userServices = userServices;
        this.communicationServices = communicationServices;
    }

/*    @ApiOperation(value = "Verify Token Service", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )*/
    @ApiOperation(value = "Add a new user. Created user is not granted to any services.", response = String.class/*, tags = "add-user"*/)
    @PostMapping(value = "/add-user", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @ApiOperation(value = "Grant a user. You should first get list of roles, then select a role to give to the created user.", response = String.class)
    @PostMapping("/grant-user")
    @PreAuthorize("hasAuthority('management:change-role')")
    public String addUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") long roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().add(roleEntity);
        managementServices.merge(userEntity);
        return "role was added to user successfully";
    }

    @ApiOperation(value = "Invoke a user. You should first get list of user roles, then select a role to invoke it.", response = String.class)
    @PostMapping("/revoke-user")
    @PreAuthorize("hasAuthority('management:change-role')")
    public String removeUserRole(
            @RequestParam("user-name") String username,
            @RequestParam("role-id") long roleId) {
        UserEntity userEntity = managementServices.getUserByUsername(username);
        RoleEntity roleEntity = roleServices.getRole(roleId);
        userEntity.getRoles().remove(roleEntity);
        managementServices.merge(userEntity);
        return "role was deleted successfully";
    }

    @ApiOperation(value = "List users. It shows all created users.", response = String.class)
    @GetMapping("/list-users")
    @PreAuthorize("hasAuthority('management:list-users')")
    public String getUsers() {
        List<UserProperties> allUsers = managementServices.getAllUsers();
        return new Gson().toJson(allUsers);
    }

    @ApiOperation(value = "List users. It shows all created users.", response = String.class)
    @GetMapping("/list-services")
    @PreAuthorize("hasAuthority('management:list-users')")
    public String getServices() {
        List<ServiceEntity> services = managementServices.getServices();
        return new Gson().toJson(services);
    }

    @ApiOperation(value = "List services by status. If input status parameter is true all active services will be shown.", response = String.class)
    @GetMapping("/list-service-by-status")
    @PreAuthorize("hasAuthority('management:list-users')")
    public String getServicesByStatus(@RequestParam("status") boolean status) {
        List<ServiceEntity> listServices = managementServices.getServicesByStatus(status);
        return new Gson().toJson(listServices);
    }

    @ApiOperation(value = "Change status of a service. you can enable or disable a service by this method.", response = String.class)
    @PostMapping("/change-service-status")
    @PreAuthorize("hasAuthority('management:change-service-status')")
    public String changeServiceStatus(
            @RequestParam("service-id") long serviceId,
            @RequestParam("status") boolean status) {
        int res = managementServices.changeServiceStatus(serviceId, status);
        return "done successfully";
    }

    @ApiOperation(value = "Access user to invoke a service. A granted user needs to have access to call a service ", response = String.class)
    @PostMapping("/service-access-to-user")
    @PreAuthorize("hasAuthority('management:service-access-to-user')")
    public String giveServiceAccessToUser(
            @RequestParam("user-name") String username,
            @RequestParam("service-id") long serviceId) {
        managementServices.giveUserAccessToUser(username, serviceId);
        return "done successfully";
    }

    @ApiOperation(value = "Change status of user services. You can disable or enable a user service.", response = String.class)
    @PostMapping("/change-user-service-status")
    public String changeUserServiceStatus(
            @RequestParam("user-name") String username,
            @RequestParam("service-id") long serviceId,
            @RequestParam("status") boolean status) {
        managementServices.changeUserServiceStatus(username, serviceId, status);
        return "done successfully";
    }
}
