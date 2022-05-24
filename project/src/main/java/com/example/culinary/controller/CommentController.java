package com.example.culinary.controller;

import com.example.culinary.entity.Comment;
import com.example.culinary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RestController
public class CommentController {
    @Autowired private CommentService commentService;
    @PostMapping("/postComment/")
    public String postComment(
            @Valid @RequestBody Comment c)
    {
        return commentService.postComment(c);
    }

    @GetMapping("/getRecipeComments/{id}")
    public List<List<String>> getRecipeComments(@PathVariable("id") int recipeId){
        return commentService.getRecipeComments(recipeId);
    }

}
