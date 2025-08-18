package com.totaliteaShop;

import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestConfig{

        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Autowired
    UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        User john = new User("john");
    }
}
