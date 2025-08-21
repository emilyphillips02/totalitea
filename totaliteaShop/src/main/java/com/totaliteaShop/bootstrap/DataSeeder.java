package com.totaliteaShop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("!test") // disables whole class when 'test' profile is active
public class DataSeeder {
}

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedTestUser(JdbcTemplate jdbc) {
        return args -> {
            String email = "user@example.com";
            Integer count = jdbc.queryForObject(
                    "select count(*) from users where email = ?", Integer.class, email
            );
            if (count != null && count == 0) {
                jdbc.update(
                    "insert into users(name, email, password_hash, role) values (?, ?, ?, ?)",
                    "Test User", email, "password1", "CUSTOMER"
                );
            }
        };
    }
}
