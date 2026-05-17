package com.shamimzariwala.auth.adapter.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shamimzariwala.auth.application.port.output.LoadUser;
import com.shamimzariwala.user.application.user.port.output.UserRepository;
import com.shamimzariwala.user.domain.user.User;

@Component
public class AdminUserSeeder implements CommandLineRunner {

    private final LoadUser loadUser;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AdminUserSeeder(LoadUser loadUser, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.loadUser = loadUser;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@example.com";

        if (loadUser.findByEmail(adminEmail).isEmpty()) {

            User admin = User.createAdmin(adminEmail, passwordEncoder.encode("admin123"));

            userRepository.save(admin);

            System.out.println("Default Admin User created: " + adminEmail);
        }
    }
}
