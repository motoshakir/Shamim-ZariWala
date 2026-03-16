package com.shamimzariwala.user.application.port.output;

import java.util.Optional;
import com.shamimzariwala.user.domain.model.User;

public interface UserRepository {
    public User save(User user);
    public Optional<User> findById(Long userId);
    public Optional<User> findByEmail(String email);
    public void deleteUser(Long userId);

} 