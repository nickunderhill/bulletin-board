package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getOrderedPosts(PostService.SORT.DESC));
        return "post/list";
    }

    @PostMapping("posts/new")
    public String createPost(@Valid @ModelAttribute("postNew") Post postNew,
                             BindingResult postResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        if (postResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postNew", postNew);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", postResult);
            return "redirect:/dashboard";
        }
        String currentUserLogin = request.getUserPrincipal().getName();
        postService.save(postNew, currentUserLogin);
        return "redirect:/";
    }
}
