package com.totaliteaShop.service;

import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUserByRole(String role) {
        return userRepository.findByRole(role);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
