package com.bonteck.challenge.bonteckchallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Tofigh 2/10/2022 11:39 AM
 */

@RestController
@RequestMapping("/communication")
public class CommunicationServices {

    @PostMapping("/send-sms")
    public String sendSMS() {
        return "Your message was sent successfully.";
    }

    @PostMapping("send-mail")
    public String sendMail() {
        return "your e-mail was sent successfully";
    }

    @GetMapping("get-world-news")
    public String getWorldNews() {
        return "The world news:  bla bla ...";
    }
}