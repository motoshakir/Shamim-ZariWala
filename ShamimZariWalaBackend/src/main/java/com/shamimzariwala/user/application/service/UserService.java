package com.shamimzariwala.user.application.service;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.shamimzariwala.user.application.command.CreateUserCommand;
import com.shamimzariwala.user.application.command.UpdateUserCommand;
import com.shamimzariwala.user.application.port.input.CreateUserUseCase;
import com.shamimzariwala.user.application.port.input.GetUserQuery;
import com.shamimzariwala.user.application.port.input.UpdateUserUseCase;
import com.shamimzariwala.user.application.port.output.UserRepository;
import com.shamimzariwala.user.domain.exception.UserNotFoundException;
import com.shamimzariwala.user.domain.model.User;

public class UserService implements CreateUserUseCase,UpdateUserUseCase,GetUserQuery {

    private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> createUser(CreateUserCommand command) {
        User user = new User(command.email(),passwordEncoder.encode(command.password()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(UpdateUserCommand command) {
        return userRepository.findById(command.userId())
            .map(user -> {
                if (command.email() != null) user.updateEmail(command.email());
                if (command.password() != null) user.changePassword(passwordEncoder.encode(command.password()));
                if (command.role() != null) user.changeRole(command.role());
                if (command.status() != null) user.changeStatus(command.status());
                
                return userRepository.save(user);
            })
            .orElseThrow(() -> new UserNotFoundException(command.userId()));
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}