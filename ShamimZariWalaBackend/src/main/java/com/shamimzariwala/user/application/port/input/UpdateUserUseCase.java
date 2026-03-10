package com.shamimzariwala.user.application.port.input;

import com.shamimzariwala.user.application.command.UpdateUserCommand;
import com.shamimzariwala.user.domain.model.User;

public interface UpdateUserUseCase {
    public User update(UpdateUserCommand command);
    
}
