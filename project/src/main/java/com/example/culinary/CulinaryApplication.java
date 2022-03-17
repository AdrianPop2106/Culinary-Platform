package com.example.culinary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
public class CulinaryApplication{

    public static void main(String[] args)  {
        SpringApplication.run(CulinaryApplication.class, args);
    }

    /*@GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }*/
   /* @Override
    public void run(String... args) throws Exception {
        String sql = "call addChefs(?,?,?,?);";

        int result = jdbcTemplate.update(sql, "Ionescu Lucian", "parolat","luci23@yahoo.com", "0");

        if (result > 0) {
            System.out.println("A new row has been inserted.");
        }

    }*/

}
