package com.bonteck.challenge.bonteckchallenge.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali Tofigh 2/10/2022 11:39 AM
 */

@RestController
@RequestMapping("/communication")
public class CommunicationServices {

    @PostMapping("/send-sms")
    @PreAuthorize("hasAnyRole('ROLE_ROLE_3')")
    public String sendSMS(
            @RequestParam("user-name") String username,
            @RequestParam("mobile-no") String mobileNo,
            @RequestParam("message") String message) {

        return "Your message was sent successfully.";
    }

    @PostMapping("send-mail")
    @PreAuthorize("hasAnyRole('ROLE_ROLE_4')")
    public String sendMail() {
        return "your e-mail was sent successfully";
    }

    @GetMapping("get-world-news")
    @PreAuthorize("hasAnyRole('ROLE_ROLE_5')")
    public String getWorldNews() {
        return "The world news:  bla bla ...";
    }
}
