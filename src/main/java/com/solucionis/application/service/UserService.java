package com.solucionis.application.service;

import com.solucionis.domain.models.User;
import com.solucionis.infrastructure.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByCpf(user.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }
}
