package com.example.culinary.service;

import com.example.culinary.entity.Comment;
import com.example.culinary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String postComment(Comment c) {
        String sql = "call culinary.postComment(?, ?, ?);";
        int result = jdbcTemplate.update(sql, c.getUserId(), c.getRecipeId(), c.getMessage());
        if(result>0){
            int id=jdbcTemplate.queryForObject("Select id from culinary.comments where id=(Select LAST_INSERT_ID())",Integer.class);
            c.setId(id);
        }
        if(c.getId()!=0)
            return "Comment added succesfully";
        else
            return "Comment already exists";
    }

    @Override
    public List<List<String>> getRecipeComments(int recipeId) {
        List<List<String>> comments = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getRecipeComments(?);",recipeId);
        for (Map row : rows) {
            String[] info=new String[3];
            info[0]=row.get("userName").toString();
            info[1]=row.get("commentDate").toString();
            info[2]=row.get("message").toString();
            List<String> data=new ArrayList<>();
            data.add(info[0]);
            data.add(info[1]);
            data.add(info[2]);
            comments.add(data);
        }
        return comments;
    }
}
