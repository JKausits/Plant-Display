package com.cgcglass.plantdisplay.RestControllers;

import com.cgcglass.plantdisplay.Domains.Post;
import com.cgcglass.plantdisplay.Models.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostRESTController {
    private PostService postService;

    @Autowired
    public PostRESTController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    public List<Post> getPosts(){
        return this.postService.getPosts();
    }

    @GetMapping("posts/active")
    public List<Post> getActivePosts(){
        return this.postService.getActivePosts();
    }

    @PostMapping("/post")
    public int createPost(@RequestBody Post post){
        return this.postService.createPost(post);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable(value = "id") String id){
        this.postService.deletePost(UUID.fromString(id));
    }

    @PatchMapping("/post/{id}")
    public void updatePost(@PathVariable(value="id") String id, @RequestBody Post post){
        post.setId(UUID.fromString(id));
        this.postService.updatePost(post);
    }
}
