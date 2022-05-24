package com.example.culinary.service;

import com.example.culinary.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    String postComment(Comment c);

    List<List<String>> getRecipeComments(int recipeId);
}
