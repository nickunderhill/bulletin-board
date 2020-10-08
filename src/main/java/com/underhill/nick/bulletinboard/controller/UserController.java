package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.service.UserService;
import com.underhill.nick.bulletinboard.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @PostMapping("/users/edit")
    public String editUser(@Valid @ModelAttribute("user") User user,
                           BindingResult userResult,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("userFeedback", user);
        if (userResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", userResult);
            return "redirect:/dashboard";
        }
        userService.update(user);
        redirectAttributes.addFlashAttribute("successMessage", "Changes has been applied");
        return "redirect:/dashboard";
    }

}


