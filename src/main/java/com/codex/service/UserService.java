package com.codex.service;

import com.codex.DTO.UserDTO;
import com.codex.exceptions.UserNotFoundException;
import com.codex.model.User;
import com.codex.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = repo.save(user);
        return modelMapper.map(newUser, UserDTO.class);
    }

    public UserDTO getOneUser(long userId) {
        Optional<User> optionalUser = repo.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = optionalUser.get();

        return modelMapper.map(user, UserDTO.class);

    }


    public List<User> getUsers() {
        return repo.findAll();
    }

    public User updateUser(Long userId, User newUser) {

        Optional<User> optionalUser = repo.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = optionalUser.get();

        if (newUser.getName()  != null) {
            user.setName(newUser.getName());
        }
        if (newUser.getPassword()  != null) {
            user.setPassword(newUser.getPassword());
        }

        return repo.save(user);
    }

    public boolean deleteUser (long userId) {
        if (repo.existsById(userId)) {
            repo.deleteById(userId);
            return true;
        }
        return false;
    }


}
