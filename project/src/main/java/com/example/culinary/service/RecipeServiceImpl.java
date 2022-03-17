package com.example.culinary.service;

import com.example.culinary.entity.Ingredient;
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
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getIngredients();");
        for (Map row : rows) {
            String[] info=new String[3];
            boolean custom;
            info[0]=row.get("id").toString();
            info[1]=row.get("title").toString();
            info[2]=row.get("description").toString();
            custom= row.get("custom").equals("true");
            Ingredient ingredient=new Ingredient(Integer.parseInt(info[0]),info[1],info[2],custom);

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
}
