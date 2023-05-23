package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("postlist", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getReferenceById(id));
        model.addAttribute("user", userDao.getReferenceById(1L));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getReferenceById(id));
        model.addAttribute("user", userDao.getReferenceById(1L));
        return "posts/edit";
    }
    @PostMapping(path = "/posts/{id}/edit")
    public String postEdit(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }


    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postPost(@ModelAttribute Post post) {
        emailService.prepareAndSend(userDao.getReferenceById(1L), post.getTitle(), post.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping(path = "/posts/create")
//    public String postPost(@RequestParam String title, @RequestParam String body, @RequestParam long id) {
//        Post newPost = new Post(title, body, userDao.getReferenceById(id));
//        postDao.save(newPost);
//        return "redirect:/posts";
//    }

}
