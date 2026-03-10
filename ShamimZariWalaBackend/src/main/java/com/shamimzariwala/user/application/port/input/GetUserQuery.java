package com.shamimzariwala.user.application.port.input;

import java.util.Optional;
import com.shamimzariwala.user.domain.model.User;

public interface GetUserQuery {
    Optional<User> findUserById(Long userId); // Wrap User in Optional
}