package com.example.culinary.service;

import com.example.culinary.entity.Ingredient;

import java.util.List;

public interface RecipeService {
        // Save operation
        Ingredient postIngredient(Ingredient user);

        // Read operation
        List<Ingredient> getIngredients();

        // Update operation
        Ingredient putIngredient(Ingredient user, int ingredientId);

        // Delete operation
        void deleteIngredientById(int ingredientId);

}
