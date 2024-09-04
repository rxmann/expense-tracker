package com.codex.controller;

import com.codex.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userController {

    @GetMapping("me")
    public String getUser () {
        return "Roman";
    }

    @GetMapping("/users")
    public List<User> getUsers () {

        List<User> users = new ArrayList<>();
        for (int i=0; i<10; i++) {
            String name = "Name "  + i+1;
            int age = i+20;
            double weight = i * 6.4;

            users.add(new User(name, age, weight));
        }

        return users;
    }

}
