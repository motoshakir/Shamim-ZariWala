package com.shamimzariwala.user.application.service;
import java.util.Optional;

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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserCommand command) {
        User user = new User(command.email(), command.password());
        return userRepository.save(user);
    }

    @Override
    public User update(UpdateUserCommand command) {
        return userRepository.findById(command.userId())
            .map(user -> {
                if (command.email() != null) user.updateEmail(command.email());
                if (command.password() != null) user.changePassword(command.password());
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