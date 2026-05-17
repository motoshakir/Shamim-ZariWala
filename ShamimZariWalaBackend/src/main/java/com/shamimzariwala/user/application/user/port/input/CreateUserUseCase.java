package com.shamimzariwala.user.application.user.port.input;

import com.shamimzariwala.user.application.user.command.CreateUserCommand;
import com.shamimzariwala.user.domain.user.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand command);
}
