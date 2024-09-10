package com.codex.config;

import com.codex.exceptions.UserNotFoundException;
import com.codex.model.User;
import com.codex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repo.findByName(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = optionalUser.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(getRoles(user.getRole()))
                .build();
    }

    private String[] getRoles(String role) {
        if (role == null || role.isEmpty() || role.equalsIgnoreCase("USER")) {
            return new String[]{"USER"};
        }
        else {
            return new String[]{"USER", "ADMIN"};
        }
    }

}


