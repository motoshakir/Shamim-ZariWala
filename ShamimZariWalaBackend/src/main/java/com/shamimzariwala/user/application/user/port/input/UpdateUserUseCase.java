package com.shamimzariwala.user.application.user.port.input;

import com.shamimzariwala.user.application.user.command.UpdateUserCommand;
import com.shamimzariwala.user.domain.user.User;

public interface UpdateUserUseCase {
    User update(UpdateUserCommand command);
}
