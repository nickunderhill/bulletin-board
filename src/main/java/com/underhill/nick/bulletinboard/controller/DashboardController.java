package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String renderDashboard(Model model,
                                  @ModelAttribute("postNew") Post postNew,
                                  HttpServletRequest request) {
        String currentUserLogin =  request.getUserPrincipal().getName();
        model.addAttribute("user", userService.getUserByEmail(currentUserLogin));
        model.addAttribute("post", postNew != null ? postNew : new Post());
        return "/dashboard";
    }
}
