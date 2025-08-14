package com.totaliteaShop.repository;

import com.totaliteaShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find all users with a specific role
    List<User> findByRole(String role);
}
