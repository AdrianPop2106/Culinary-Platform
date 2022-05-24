package com.example.culinary.service;

import com.example.culinary.entity.Ingredient;
import com.example.culinary.entity.Recipe;

import java.util.List;

public interface RecipeService {
        // Save operation
        Ingredient postIngredient(Ingredient user);

        Recipe postRecipe(Recipe recipe);

        // Read operation
        List<Ingredient> getIngredients();

        // Update operation
        Ingredient putIngredient(Ingredient user, int ingredientId);

        // Delete operation
        void deleteIngredientById(int ingredientId);

        Recipe getRecipeById(int recipeId);

        List<Ingredient> getRecipeIngredients(int recipeId);

        String saveRecipe(int userId,int recipeId);

        List<Recipe> getSavedRecipes(int userId);
}
