package com.shamimzariwala.user.adapter.output.persistance;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository
        extends JpaRepository<UserEntity, Long> {
            Optional<UserEntity> findByEmail(String email);
}