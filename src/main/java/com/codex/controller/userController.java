package com.codex.controller;

import com.codex.exceptions.UserNotFoundException;
import com.codex.model.User;
import com.codex.service.UserService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
    }

    @GetMapping
    public List<User> getUsers () {
        return service.getUsers();
    }

    @PatchMapping
    public ResponseEntity<?> updateUser (@RequestParam Long userId, @RequestBody User user) {
        User updatedUser = service.updateUser(userId, user);
        if (updatedUser.getId()  != null) {
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser (@PathVariable int userId) {
        boolean deleted = service.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }





}
