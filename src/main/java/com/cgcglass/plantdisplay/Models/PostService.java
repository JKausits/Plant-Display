package com.cgcglass.plantdisplay.Models;

import com.cgcglass.plantdisplay.Domains.Post;
import com.cgcglass.plantdisplay.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(){
        return this.postRepository.getPosts();
    }

    public List<Post> getActivePosts(){
        return this.postRepository.getActivePosts();
    }

    public void deletePost(UUID id){
        this.postRepository.deletePost(id);
    }

    public int createPost(Post post){
        return this.postRepository.createPost(post);
    }

    public void updatePost(Post post){
        this.postRepository.updatePost(post);
    }

}
