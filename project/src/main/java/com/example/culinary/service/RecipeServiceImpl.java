package com.example.culinary.service;

import com.example.culinary.entity.Ingredient;
import com.example.culinary.entity.Recipe;
import com.example.culinary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ingredient postIngredient(Ingredient i) {
        String sql = "call culinary.postIngredient(?, ?, ?);";
        int result = jdbcTemplate.update(sql, i.getTitle(), i.getDescription(), i.getCustom());
        if(result>0){
            i.setId(jdbcTemplate.queryForObject("Select id from culinary.users where id=(Select LAST_INSERT_ID())",Integer.class));
        }
        return i;
    }

    @Override
    public Recipe postRecipe(Recipe i) {
        String sql = "call culinary.postRecipe(?, ?, ?);";
        int result = jdbcTemplate.update(sql, i.getChefId(), i.getTitle(), i.getDescription());
        if(result>0){
            i.setId(jdbcTemplate.queryForObject("Select id from culinary.recipe where id=(Select LAST_INSERT_ID())",Integer.class));
        }
        int id=i.getId();

        return i;
    }

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getIngredients();");
        for (Map row : rows) {
            String[] info=new String[3];
            boolean custom;
            info[0]=row.get("id").toString();
            info[1]=row.get("title").toString();
            info[2]=row.get("description").toString();
            if(row.get("custom").toString().equals("1"))
                custom= true;
            else
                custom=false;
            Ingredient ingredient=new Ingredient(Integer.parseInt(info[0]),info[1],info[2],custom,0,null);

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @Override
    public Ingredient putIngredient(Ingredient ingredient, int ingredientId) {
        String sql = "call culinary.putIngredient(?, ?, ?,?);";
        int result=jdbcTemplate.update(sql,ingredientId,ingredient.getTitle(),ingredient.getDescription(),ingredient.getCustom());
        if(result>0){
            ingredient.setId(ingredientId);
        }
        return ingredient;
    }

    @Override
    public void deleteIngredientById(int ingredientId) {
        jdbcTemplate.update("call culinary.deleteIngredient(?)",ingredientId);
    }

    @Override
    public Recipe getRecipeById(int id) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getRecipeById(?);",id);
        for (Map row : rows) {
            String[] info=new String[4];
            info[0]=row.get("chefId").toString();
            info[1]=row.get("title").toString();
            info[2]=row.get("description").toString();
            return new Recipe(id,Integer.parseInt(info[0]),info[1],info[2]);
        }
        return null;
    }

    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getIngredientRecipe(?);",recipeId);
        for (Map row : rows) {
            String[] info=new String[5];
            boolean custom;
            info[0]=row.get("id").toString();
            info[1]=row.get("title").toString();
            info[2]=row.get("description").toString();
            if(row.get("custom").toString().equals("1"))
                custom= true;
            else
                custom=false;
            info[3]=row.get("quantity").toString();
            info[4]=row.get("measure").toString();
            Ingredient ingredient=new Ingredient(Integer.parseInt(info[0]),info[1],info[2],custom,Integer.parseInt(info[3]),info[4]);

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @Override
    public String saveRecipe(int userId, int recipeId) {
        List<Recipe> r=getSavedRecipes(userId);
        for(Recipe i:r)
            if(i.getId()==recipeId)
                return "Recipe already exists";
        String sql = "call culinary.saveRecipe(?, ?);";
        int result = jdbcTemplate.update(sql, userId,recipeId);
        if(result>0)
            return "Recipe saved!";
        else
            return "Recipe not saved!";

    }

    @Override
    public List<Recipe> getSavedRecipes(int userId) {
        List<Recipe> recipes = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getSavedRecipes(?);",userId);
        for (Map row : rows) {
            String[] info=new String[4];
            info[0]=row.get("id").toString();
            info[1]=row.get("chefId").toString();
            info[2]=row.get("title").toString();
            info[3]=row.get("description").toString();
            Recipe recipe=new Recipe(Integer.parseInt(info[0]),Integer.parseInt(info[1]),info[2],info[3]);

            recipes.add(recipe);
        }
        return recipes;
    }
}
