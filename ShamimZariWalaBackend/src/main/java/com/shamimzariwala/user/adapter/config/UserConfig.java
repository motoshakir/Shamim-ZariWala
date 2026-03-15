package com.shamimzariwala.user.adapter.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shamimzariwala.user.application.port.output.UserRepository;
import com.shamimzariwala.user.application.service.UserService;

    @Configuration
    public class UserConfig {

        @Bean
        public UserService userService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
            return new UserService(userRepository,passwordEncoder);
        }
    }
