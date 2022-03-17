package com.example.culinary.controller;

import com.example.culinary.entity.Chef;
import com.example.culinary.entity.User;
import com.example.culinary.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("/users/")
    public String postUser(
            @Valid @RequestBody User user)
    {
        return userService.postUser(user);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/users")
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int userId)
    {
        return userService.getUserById(userId);
    }

    @PutMapping("/users/{id}")
    public User putUser(@RequestBody User user, @PathVariable("id") int userId) {

        return userService.putUser(user,userId);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") int userId) {
        return userService.deleteUserById(userId);
    }

    @PostMapping("/postChef/")
    public String postChef(
            @Valid @RequestBody Chef chef)
    {
        return userService.postChef(chef);
    }

    @GetMapping("/getChefs")
    public List<Chef> getChefs()
    {
        return userService.getChefs();
    }

    @PutMapping("/putChef/{id}")
    public Chef putChef(@RequestBody Chef chef, @PathVariable("id") int userId) {
        return userService.putChef(chef,userId);
    }
}
