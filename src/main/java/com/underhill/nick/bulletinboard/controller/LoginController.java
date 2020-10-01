package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getUserLoginPage() {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }
}
