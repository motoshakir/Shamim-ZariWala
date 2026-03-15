package com.shamimzariwala.user.application.port.input;
import java.util.Optional;

import com.shamimzariwala.user.application.command.CreateUserCommand;
import com.shamimzariwala.user.domain.model.User;

public interface CreateUserUseCase {
    public Optional<User> createUser(CreateUserCommand command);
}