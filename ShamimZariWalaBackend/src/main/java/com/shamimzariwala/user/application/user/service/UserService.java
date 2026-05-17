package com.shamimzariwala.user.application.user.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.user.application.user.command.CreateUserCommand;
import com.shamimzariwala.user.application.user.command.UpdateUserCommand;
import com.shamimzariwala.user.application.user.port.input.CreateUserUseCase;
import com.shamimzariwala.user.application.user.port.input.DeleteUserUseCase;
import com.shamimzariwala.user.application.user.port.input.GetUserQuery;
import com.shamimzariwala.user.application.user.port.input.UpdateUserUseCase;
import com.shamimzariwala.user.application.user.port.output.UserRepository;
import com.shamimzariwala.user.domain.user.User;
import com.shamimzariwala.user.domain.user.UserAlreadyExistsException;
import com.shamimzariwala.user.domain.user.UserNotFoundException;

public class UserService implements CreateUserUseCase, UpdateUserUseCase, GetUserQuery, DeleteUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(CreateUserCommand command) {
        userRepository.findByEmail(command.email()).ifPresent(u -> {
            throw new UserAlreadyExistsException(command.email());
        });
        User user = User.create(command.email(), passwordEncoder.encode(command.password()));

        user.updateProfile(command.firstName(), command.lastName(), command.phoneNumber(), command.avatar(),
                command.gender(), command.dateOfBirth());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UpdateUserCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        if (command.email() != null) {
            user.updateEmail(command.email());
        }

        if (command.password() != null && !command.password().isBlank()) {
            user.changePassword(passwordEncoder.encode(command.password()));
        }

        user.updateProfile(
                command.firstName(),
                command.lastName(),
                command.phoneNumber(),
                command.avatar(),
                command.gender(),
                command.dateOfBirth());

        if (command.role() != null)
            user.changeRole(command.role());
        if (command.status() != null)
            user.changeStatus(command.status());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
