package com.example.culinary.controller;

import com.example.culinary.entity.Ingredient;
import com.example.culinary.entity.User;
import com.example.culinary.service.RecipeService;
import com.example.culinary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/postIngredient/")
    public Ingredient postIngredient(
            @Valid @RequestBody Ingredient i)
    {
        return recipeService.postIngredient(i);
    }

    @GetMapping("/getIngredients")
    public List<Ingredient> getIngredients()
    {
        return recipeService.getIngredients();
    }

    @PutMapping("/putIngredient/{id}")
    public Ingredient putIngredient(@RequestBody Ingredient i, @PathVariable("id") int ingredientId) {

        return recipeService.putIngredient(i,ingredientId);
    }

    @DeleteMapping("/deleteIngredientById/{id}")
    public String deleteUserById(@PathVariable("id") int userId) {
        recipeService.deleteIngredientById(userId);
        return "Deleted Successfully";
    }
}
