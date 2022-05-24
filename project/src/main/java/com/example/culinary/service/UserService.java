package com.example.culinary.service;

import com.example.culinary.entity.Chef;
import com.example.culinary.entity.User;

import java.util.List;

public interface UserService {
    // Save operation
    String postUser(User user);

    // Read operation
    List<User> getUsers();

    User getUserById(int id);

    Integer login(User user);

    // Update operation
    User putUser(User user, int userId);

    // Delete operation
    String deleteUserById(int userId);

    String postChef(Chef chef);

    List<Chef> getChefs();

    Chef putChef(Chef chef,int userId);

    String getChefByRecipeId(int recipeId);

}
