package com.totaliteaShop.service;

import com.totaliteaShop.model.UserModel;
import com.totaliteaShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<UserModel> getAllUserByRole(String role) {
        return userRepository.findByRole(role);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
