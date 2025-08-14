package com.totaliteaShop.service;

import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRespository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(UserRepository userRespository, BCryptPasswordEncoder passwordEncoder) {
        this.userRespository = userRespository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }
    public User registerUser(String name, String email, String rawPassword) {
        if (userRespository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user .setPasswordHash(bCryptPasswordEncoder.encode(rawPassword));

        return userRespository.save(user);
    }
}
