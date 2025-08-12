package com.totaliteaShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TotaliteaApplicationTests {

    @Test
    void contextLoads() {
        // no-op: verifies the Spring context starts with the test profile
    }
}
