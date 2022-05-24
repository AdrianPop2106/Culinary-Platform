package com.example.culinary.service;

import com.example.culinary.entity.Chef;
import com.example.culinary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String postUser(User user) {
        String sql = "call culinary.postUser(?, ?, ?);";
        int result = jdbcTemplate.update(sql, user.getUserName(), user.getPass(), user.getEmail());
        if(result>0){
            int id=jdbcTemplate.queryForObject("Select id from culinary.users where id=(Select LAST_INSERT_ID())",Integer.class);
                user.setId(id);
        }
        if(user.getId()!=0)
            return "User added succesfully";
        else
            return "User already exists";
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getUsers();");
        for (Map row : rows) {
            String[] info=new String[4];
            info[0]=row.get("id").toString();
            info[1]=row.get("userName").toString();
            info[2]=row.get("email").toString();
            info[3]=row.get("pass").toString();
            User user=new User(Integer.parseInt(info[0]),info[1],info[2],info[3]);

            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        User u;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getUserById(?);",id);
        for (Map row : rows) {
            String[] info=new String[4];
            info[0]=row.get("userName").toString();
            info[1]=row.get("email").toString();
            info[2]=row.get("pass").toString();
            return new User(id,info[0],info[1],info[2]);
        }
        return null;
    }

    @Override
    public Integer login(User user) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("call culinary.login(?,?);",user.getEmail(),user.getPass());
        if(result.size()==0)
            return -1;
        for (Map row : result) {
            String info;
            info=row.get("id").toString();
            return Integer.parseInt(info);
        }
        return null;
    }

    @Override
    public User putUser(User user, int userId) {
        String sql = "call culinary.putUser(?, ?, ?,?);";
        int result=jdbcTemplate.update(sql,userId,user.getUserName(),user.getEmail(),user.getPass());
        if(result>0){
            user.setId(userId);
        }
        return user;
    }

    @Override
    public String deleteUserById(int userId) {
        int result=jdbcTemplate.update("call culinary.deleteUser(?)",userId);
        if(result>0)
            return "User deleted succesfully";
        else
            return "User doesn't exist";
    }

    @Override
    public String postChef(Chef chef){
        String sql = "call culinary.postChef(?, ?, ?,?);";
        int result = jdbcTemplate.update(sql, chef.getUserName(), chef.getPass(), chef.getEmail(),chef.getOrdering());
        if(result>0){
            chef.setId(jdbcTemplate.queryForObject("Select id from culinary.users where id=(Select LAST_INSERT_ID())",Integer.class));
        }
        if(chef.getId()==0)
            return "User already exists";
        else
            return "User added succesfully";
    }

    public List<Chef> getChefs(){
        List<Chef> chefs = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("call culinary.getChefs();");
        for (Map row : rows) {
            String[] info=new String[4];
            boolean ordering;
            info[0]=row.get("id").toString();
            info[1]=row.get("userName").toString();
            info[2]=row.get("email").toString();
            info[3]=row.get("pass").toString();
            ordering=(boolean)row.get("ordering");
            Chef c=new Chef(Integer.parseInt(info[0]),info[1],info[2],info[3],ordering);

            chefs.add(c);
        }
        return chefs;
    }

    @Override
    public Chef putChef(Chef chef, int userId) {
        String sql = "call culinary.putChef(?, ?, ?,?,?);";
        int result=jdbcTemplate.update(sql,userId,chef.getUserName(),chef.getEmail(),chef.getPass(),chef.getOrdering());
        if(result>0){
            chef.setId(userId);
        }
        return chef;
    }

    @Override
    public String getChefByRecipeId(int recipeId) {
        Map<String, Object> row = jdbcTemplate.queryForList("call culinary.getChefByRecipeId(?);",recipeId).get(0);

        return row.get("username").toString();
    }
}
