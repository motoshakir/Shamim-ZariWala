package com.shamimzariwala.user.application.port.input;

import java.util.Optional;

import com.shamimzariwala.user.application.command.UpdateUserCommand;
import com.shamimzariwala.user.domain.model.User;

public interface UpdateUserUseCase {
    public Optional<User> update(UpdateUserCommand command);
    
}
