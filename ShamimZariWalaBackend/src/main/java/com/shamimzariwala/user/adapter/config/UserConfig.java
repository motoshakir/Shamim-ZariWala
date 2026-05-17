package com.shamimzariwala.user.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shamimzariwala.user.application.address.port.output.AddressRepository;
import com.shamimzariwala.user.application.address.service.AddressService;
import com.shamimzariwala.user.application.user.port.output.UserRepository;
import com.shamimzariwala.user.application.user.service.UserService;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, passwordEncoder);
    }

    @Bean
    public AddressService addressService(AddressRepository addressRepository, UserRepository userRepository) {
        return new AddressService(addressRepository, userRepository);
    }
}
