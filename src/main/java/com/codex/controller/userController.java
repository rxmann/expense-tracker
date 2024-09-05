package com.codex.controller;

import com.codex.model.User;
import com.codex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User createUser (@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser (@PathVariable int userId) {
        return service.getOneUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> getUsers () {
        return service.getUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser (@PathVariable int userId) {
        boolean deleted = service.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }





}
