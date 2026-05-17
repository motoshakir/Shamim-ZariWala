package com.shamimzariwala.user.application.user.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.user.domain.user.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
    void deleteById(Long userId);
    Page<User> findAll(Pageable pageable);
}
