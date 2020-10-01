package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.model.User;
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
                                  @ModelAttribute("postFeedback") Post post,
                                  @ModelAttribute("userFeedback") User user,
                                  @ModelAttribute("successMessage") String successMessage,
                                  HttpServletRequest request) {
        String currentUserLogin = request.getUserPrincipal().getName();
        model.addAttribute("user", user.getId() != null ? user : userService.getUserByEmail(currentUserLogin));
        model.addAttribute("post", post != null ? post : new Post());
        return "dashboard";
    }
}
