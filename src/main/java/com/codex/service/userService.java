package com.codex.service;

import com.codex.model.User;
import com.codex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User createUser(User user) {
        return repo.save(user);
    }

    public Optional<User> getOneUser(long userId) {
        return repo.findById(userId);
    }

    public void addUser(User user) {
        repo.save(user);
    }

    public List<User> getUsers() {
        return repo.findAll();
    }


    public boolean deleteUser (long userId) {
        if (repo.existsById(userId)) {
            repo.deleteById(userId);
            return true;
        }
        return false;
    }
}
