package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

//    @GetMapping("/post")
//    public String index(Model model) {
//        model.addAttribute("post", postDao.findAll());
//        return "post/index";
//    }

    @GetMapping("/posts")
    public String index(Model model) {
        Post post1 = new Post("Title1", "body1");
        Post post2 = new Post("Title2", "body2");
        List<Post> postList = new ArrayList<Post>();
        postList.add(post1);
        postList.add(post2);
        model.addAttribute("postlist", postList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable int id, Model model) {
        Post newPost = new Post("this is the title", "body");
        model.addAttribute("post", newPost);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String create() {
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postPost(@RequestParam String title, @RequestParam String body) {
        Post newPost = new Post(title, body);
        postDao.save(newPost);
        return "redirect:/posts";
    }

}
