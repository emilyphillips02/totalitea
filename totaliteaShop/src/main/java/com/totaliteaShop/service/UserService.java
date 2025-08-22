package com.totaliteaShop.service;

import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional
    public String register(User user, String rawPassword) {
        if (user.getName() == null || user.getName().isBlank()) return "Name is required.";
        if (user.getEmail() == null || user.getEmail().isBlank()) return "Email is required.";

        String email = user.getEmail().trim().toLowerCase();
        if (repo.findByEmail(email).isPresent()) return "An account with this email already exists.";

        if (rawPassword == null || rawPassword.length() < 8)
            return "Password must be at least 8 characters.";

        // hash + save
        user.setEmail(email);
        user.setRole(user.getRole() == null ? "CUSTOMER" : user.getRole());
        user.setPasswordHash(encoder.encode(rawPassword));
        user.setPassword(null);
        repo.save(user);
        return "Success";
    }
}
