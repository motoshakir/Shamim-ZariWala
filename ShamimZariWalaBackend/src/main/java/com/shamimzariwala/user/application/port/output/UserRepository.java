package com.shamimzariwala.user.application.port.output;

import java.util.Optional;
import com.shamimzariwala.user.domain.model.User;

public interface UserRepository {
    public Optional<User> save(User user);
    public Optional<User> findById(Long userId);
} 