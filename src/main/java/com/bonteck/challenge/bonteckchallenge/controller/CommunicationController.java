package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
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
        List<UserServicesEntity> activeUserServices = managementServices.getActiveUserServices(username, true);

        for (UserServicesEntity userService: activeUserServices) {
            ServiceEntity service = userService.getService();
            /*if (!service.isStatus())
                return "this service is not active for you!";*/
            if (!"send-sms".equals(service.getName()))
                return "this service is not in your services!";
            if (userService.getCount() > userService.getMax())
                return "maximum exceeded.";
            activityLogService.save(username, "send-sms");
            return "Your message was sent successfully.";
        }
        return "This service is not active!";
    }

    @PostMapping("send-mail")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_4')")
    public String sendMail(
            @RequestParam("user-name") String username,
            @RequestParam("email") String email,
            @RequestParam("message") String message) {
        activityLogService.save(username, "send-mail");
        return "your e-mail was sent successfully";
    }

    @GetMapping("get-world-news")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_5')")
    public String getWorldNews(@RequestParam("user-name") String username) {
        activityLogService.save(username, "get-world-news");
        return "The world news:  bla bla ...";
    }
}
