package com.codex.controller;

import com.codex.DTO.UserDTO;
import com.codex.exceptions.UserNotFoundException;
import com.codex.model.User;
import com.codex.security.MyUserDetailsService;
import com.codex.service.UserService;
import com.codex.security.webtoken.JwtService;
import com.codex.security.webtoken.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/register/user")
    public UserDTO registerUser (@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken (@RequestBody LoginForm loginForm) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.name(),
                loginForm.password()
        ));

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.name()));
        }
        else {
            throw new UserNotFoundException("Invalid Credentials");
        }
    }

}
