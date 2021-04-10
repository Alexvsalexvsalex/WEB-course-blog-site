package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.validator.PostCredentialsWriteValidator;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id:[0-9]{1,18}}")
    public String postGet(@PathVariable("id") Post post, Model model) {
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new Comment());
        return "PostPage";
    }

    @PostMapping("/post/{id:[0-9]{1,18}}")
    public String postPost(@Valid @ModelAttribute("commentForm") Comment comment,
                           BindingResult bindingResult,
                           @PathVariable("id") long postId,
                           HttpSession httpSession, Model model) {
        Post post = postService.findById(postId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }

        postService.writeComment(post, comment, getUser(httpSession));
        putMessage(httpSession, "You published new comment");

        return "redirect:/post/" + postId;
    }
}