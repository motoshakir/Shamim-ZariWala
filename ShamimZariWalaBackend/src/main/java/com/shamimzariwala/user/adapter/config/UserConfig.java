package com.shamimzariwala.user.adapter.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shamimzariwala.user.application.port.output.UserRepository;
import com.shamimzariwala.user.application.service.UserService;

    @Configuration
    public class UserConfig {

        @Bean
        public UserService userService(UserRepository userRepository) {
            return new UserService(userRepository);
        }
    }
