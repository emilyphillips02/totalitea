package com.totaliteaShop.registration;

import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = new BCryptPasswordEncoder();
        registrationService = new RegistrationService(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(null);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = registrationService.registerUser("Test User", "test@example.com", "password123");

        assertEquals("Test User", result.getName());
        assertEquals("test@example.com", result.getEmail());
        assertNotEquals("password123", result.getPasswordHash()); // Password should be hashed
        assertTrue(result.getPasswordHash().startsWith("$2a$")); // BCrypt hash prefix

        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_EmailAlreadyExists() {
        when(userRepository.findByEmail("duplicate@example.com")).thenReturn(Optional.of(new User()));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.registerUser("User", "duplicate@example.com", "password123")
        );

        assertEquals("Email already in use", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
