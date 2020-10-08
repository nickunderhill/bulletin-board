package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(HttpServletRequest request,
                           Model model,
                           @ModelAttribute("user") User user) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "registration";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("loginError", "User with this email already exists");
            return "registration";
        }
        userService.authWithoutPassword(user);
        return "redirect:/login";
    }


}

