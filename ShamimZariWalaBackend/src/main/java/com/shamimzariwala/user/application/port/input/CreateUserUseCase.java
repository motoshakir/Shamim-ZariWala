package com.shamimzariwala.user.application.port.input;
import com.shamimzariwala.user.application.command.CreateUserCommand;
import com.shamimzariwala.user.domain.model.User;

public interface CreateUserUseCase {
    public User createUser(CreateUserCommand command);
}