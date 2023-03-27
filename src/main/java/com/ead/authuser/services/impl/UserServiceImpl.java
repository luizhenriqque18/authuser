package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public void delete(UserModel userModel) {
        this.userRepository.delete(userModel);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
