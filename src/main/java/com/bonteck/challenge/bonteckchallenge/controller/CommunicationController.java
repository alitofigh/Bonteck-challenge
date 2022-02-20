package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserEntity;
import com.bonteck.challenge.bonteckchallenge.model.UserServicesEntity;
import com.bonteck.challenge.bonteckchallenge.service.ActivityLogService;
import com.bonteck.challenge.bonteckchallenge.service.ManagementServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 11:39 AM
 */

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    private final ManagementServices managementServices;
    private final ActivityLogService activityLogService;

    public CommunicationController(ManagementServices managementServices, ActivityLogService activityLogService) {
        this.managementServices = managementServices;
        this.activityLogService = activityLogService;
    }


    @PostMapping("/send-sms")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_3')")
    public String sendSMS(
            @RequestParam("user-name") String username,
            @RequestParam("mobile-no") String mobileNo,
            @RequestParam("message") String message) {
        ServiceEntity serviceEntity = managementServices.getServiceByName("send-sms");
        if (!serviceEntity.isStatus())
            return "this service is not active.";
        UserEntity user = managementServices.getUserByUsername(username);
        if (user != null) {
            if (!user.isEnable())
                return "user is not enabled!";
            if (!user.isNonLocked())
                return "user is locked";
            if (user.getBalance() < serviceEntity.getCost())
                return "user not have enough money to invoke this service!";

            List<UserServicesEntity> activeUserServices = managementServices.getActiveUserServices(username, true);
            boolean allowedService = false;
            for (UserServicesEntity userService : activeUserServices) {
                ServiceEntity service = userService.getService();
                if ("send-sms".equals(service.getName()) && userService.getCount() < userService.getMax() + 1) {
                    allowedService = true;
                    break;
                }
            }
            if (allowedService) {
                activityLogService.save(username, "send-sms");
                user.setBalance(user.getBalance() - serviceEntity.getCost());
                managementServices.merge(user);
                return "Your message was sent successfully.";
            } else
                return "This service is not active for you or your balance is not enough to call it!";
        } else
            return String.format("user '%s' does not exist.", username);
    }

    @PostMapping("send-mail")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_4')")
    public String sendMail(
            @RequestParam("user-name") String username,
            @RequestParam("email") String email,
            @RequestParam("message") String message) {
        ServiceEntity serviceEntity = managementServices.getServiceByName("send-mail");
        if (!serviceEntity.isStatus())
            return "this service is not active.";
        UserEntity user = managementServices.getUserByUsername(username);
        if (user != null) {
            if (!user.isEnable())
                return "user is not enabled!";
            if (!user.isNonLocked())
                return "user is locked";
            if (user.getBalance() < serviceEntity.getCost())
                return "user not have enough money to invoke this service!";
            List<UserServicesEntity> activeUserServices = managementServices.getActiveUserServices(username, true);

            boolean allowedService = false;
            for (UserServicesEntity userService : activeUserServices) {
                ServiceEntity service = userService.getService();
                if ("send-mail".equals(service.getName()) && userService.getCount() < userService.getMax() + 1) {
                    allowedService = true;
                    break;
                }
            }
            if (allowedService) {
                activityLogService.save(username, "send-mail");
                user.setBalance(user.getBalance() - serviceEntity.getCost());
                managementServices.merge(user);
                return "Your message was sent successfully.";
            } else
                return "This service is not active for you or your balance is not enough to call it!";
        } else
            return String.format("user '%s' does not exist.", username);
    }

    @GetMapping("get-world-news")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_5')")
    public String getWorldNews(@RequestParam("user-name") String username) {
        ServiceEntity serviceEntity = managementServices.getServiceByName("get-world-news");
        if (!serviceEntity.isStatus())
            return "this service is not active.";
        UserEntity user = managementServices.getUserByUsername(username);
        if (user != null) {
            if (!user.isEnable())
                return "user is not enabled!";
            if (!user.isNonLocked())
                return "user is locked";
            if (user.getBalance() < serviceEntity.getCost())
                return "user not have enough money to invoke this service!";
            List<UserServicesEntity> activeUserServices = managementServices.getActiveUserServices(username, true);

            boolean allowedService = false;
            for (UserServicesEntity userService : activeUserServices) {
                ServiceEntity service = userService.getService();
                if ("get-world-news".equals(service.getName()) && userService.getCount() < userService.getMax() + 1) {
                    allowedService = true;
                    break;
                }
            }
            if (allowedService) {
                activityLogService.save(username, "get-world-news");
                user.setBalance(user.getBalance() - serviceEntity.getCost());
                managementServices.merge(user);
                return "Your message was sent successfully.";
            } else
                return "This service is not active for you or your balance is not enough to call it!";
        } else
            return String.format("user '%s' does not exist.", username);
    }
}
