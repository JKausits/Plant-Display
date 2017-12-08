package com.cgcglass.plantdisplay.RowMappers;

import com.cgcglass.plantdisplay.Domains.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PostRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();
        post.setId((UUID) resultSet.getObject("ID"));
        post.setContent(resultSet.getString("content"));
        post.setActive(resultSet.getBoolean("active"));

        return post;
    }
}
