package com.shamimzariwala.auth.adapter.config;


import com.shamimzariwala.auth.application.port.output.LoadUser;
import com.shamimzariwala.user.application.port.output.UserRepository;
import com.shamimzariwala.user.domain.model.User;
import com.shamimzariwala.user.domain.model.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AdminUserSeeder implements CommandLineRunner {

    private final LoadUser loadUser;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public AdminUserSeeder(LoadUser loadUser, PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.loadUser = loadUser;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@example.com";

        // 1. Check if admin already exists
        if (loadUser.findByEmail(adminEmail).isEmpty()) {
            
            // 2. Create the admin user
            User admin = new User(adminEmail, passwordEncoder.encode("admin123"), UserRole.SYSTEM_ADMIN);
           
            // 5. Save to database
            userRepository.save(admin);
            
            System.out.println("Default Admin User created: " + adminEmail);
        }
    }
}
