package com.bonteck.challenge.bonteckchallenge.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Tofigh 2/10/2022 11:39 AM
 */

@RestController
@RequestMapping("/communication")
public class CommunicationServices {

    @PostMapping
    public String sendSMS() {
        return "Your message was sent successfully.";
    }

    public String sendMail() {
        return "your e-mail was sent successfully";
    }

    public String getWorldNews() {
        return "The world news:  bla bla ...";
    }
}
