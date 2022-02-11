package com.bonteck.challenge.bonteckchallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Tofigh 2/7/2022 11:33 AM
 */

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView() {
        System.out.println("this method was called.");
        return "login";
    }
}
