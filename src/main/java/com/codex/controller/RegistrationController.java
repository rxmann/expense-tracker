package com.codex.controller;

import com.codex.DTO.UserDTO;
import com.codex.model.User;
import com.codex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    @Autowired
    private UserService service;

    @PostMapping("/register/user")
    public UserDTO registerUser (@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/logined")
    public UserDTO loginUser (@RequestBody User user) {
        return service.registerUser(user);
    }




}
