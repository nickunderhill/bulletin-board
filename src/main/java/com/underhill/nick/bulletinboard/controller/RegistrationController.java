package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String register(HttpServletRequest request) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping
    public String register(@Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("error", true);
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("message", "Check your password input");
            return "registration";
        }
        boolean userExists = userService.createOrUpdateUser(user);
        if (userExists) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }
        return "redirect:/login";
    }


}

