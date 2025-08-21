package com.totaliteaShop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("!test") // disables whole class when 'test' profile is active
public class DataSeeder {

    @Bean
    CommandLineRunner seedTestUser(JdbcTemplate jdbc, PasswordEncoder encoder) { // <-- inject
        return args -> {
            String email = "user@example.com";
            String raw   = "password1";

            Integer count = jdbc.queryForObject(
                    "select count(*) from users where email = ?", Integer.class, email
            );

            if (count == null || count == 0) {
                jdbc.update(
                        "insert into users(name, email, password_hash, role) values (?, ?, ?, ?)",
                        "Test User", email, encoder.encode(raw), "CUSTOMER"
                );
            } else {
                String stored = jdbc.queryForObject(
                        "select password_hash from users where email = ?", String.class, email
                );
                if (stored != null && !stored.startsWith("$2") && !stored.startsWith("{bcrypt}$2")) {
                    jdbc.update(
                            "update users set password_hash = ? where email = ?",
                            encoder.encode(raw), email
                    );
                }
            }
        };
    }
}
