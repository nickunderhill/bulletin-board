package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.service.UserService;
import com.underhill.nick.bulletinboard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getUserLoginPage() {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }
}
