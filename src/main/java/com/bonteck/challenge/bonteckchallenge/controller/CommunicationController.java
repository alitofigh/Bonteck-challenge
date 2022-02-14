package com.bonteck.challenge.bonteckchallenge.controller;

import com.bonteck.challenge.bonteckchallenge.repository.ActivityLogRepository;
import com.bonteck.challenge.bonteckchallenge.service.ActivityLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali Tofigh 2/10/2022 11:39 AM
 */

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    private final ActivityLogService activityLogService;

    public CommunicationController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }


    @PostMapping("/send-sms")
    @PreAuthorize("hasAnyRole('ROLE_LEVEL_3')")
    public String sendSMS(
            @RequestParam("user-name") String username,
            @RequestParam("mobile-no") String mobileNo,
            @RequestParam("message") String message) {

        activityLogService.save(username, "send-sms");
        return "Your message was sent successfully.";
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
