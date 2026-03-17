package com.shamimzariwala.user.application.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.user.domain.model.User;

public interface UserRepository {
    public User save(User user);
    public Optional<User> findById(Long userId);
    public Optional<User> findByEmail(String email);
    public void deleteById(Long userId);
    public Page<User> findAll(Pageable pageable);

} 