package com.totaliteaShop.service;

import com.totaliteaShop.model.User;
import com.totaliteaShop.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String register(User user, String rawPassword) {
        if (userRepository .findByEmail (user.getEmail()).isPresent()) {
            return "Email already registered";
        }
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        if (user .getRole()==null) {
            user.setRole("CUSTOMER");
        }
        user.setDateRegistered(Instant.now());
        userRepository.save(user);
        return "success";
    }
}
