package com.cgcglass.plantdisplay.Controllers;

import com.cgcglass.plantdisplay.Domains.Post;
import com.cgcglass.plantdisplay.Models.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class PlantDisplayController {

    private PostService postService;

    public PlantDisplayController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String postsPage(Model model){
        model.addAttribute("title", "Posts");
        List<Post> posts = this.postService.getPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/post/new")
    public String newPostForm(Model model){
        model.addAttribute("title", "New Post");
        return "postForm";
    }

    @GetMapping("")
    public String display(Model model){
        return "display";
    }


}
