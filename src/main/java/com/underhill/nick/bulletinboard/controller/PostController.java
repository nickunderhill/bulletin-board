package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.model.Post;
import com.underhill.nick.bulletinboard.service.PostService;
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
    public String getPosts(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 9;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("posts", postService.getOrderedPosts(PostService.SORT.DESC, page, size));
        return "post/list";
    }

    @PostMapping("posts/new")
    public String createPost(@Valid @ModelAttribute("postFeedback") Post post,
                             BindingResult postResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        if (postResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postFeedback", post);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", postResult);
            return "redirect:/dashboard";
        }
        String currentUserLogin = request.getUserPrincipal().getName();
        postService.save(post, currentUserLogin);
        return "redirect:/";
    }
}
