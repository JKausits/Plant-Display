package com.cgcglass.plantdisplay.Repositories;

import com.cgcglass.plantdisplay.Domains.Post;
import com.cgcglass.plantdisplay.RowMappers.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createPost(Post post) {
        String sql = "INSERT INTO post VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{post.getId(), post.getContent(), post.isActive()});
    }

    public List<Post> getPosts(){
        List<Post> posts;
        String sql = "SELECT * FROM post";
        posts = jdbcTemplate.query(sql, new PostRowMapper());
        return posts;
    }

    public List<Post> getActivePosts(){
        List<Post> posts;
        String sql = "SELECT * FROM post WHERE active = true";
        posts = jdbcTemplate.query(sql, new PostRowMapper());
        return posts;
    }

    public void deletePost(UUID id){
        String sql = "DELETE post WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    public void updatePost(Post post){
        String sql = "UPDATE post SET content = ?, active = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{post.getContent(), post.isActive(), post.getId()});
    }
}
