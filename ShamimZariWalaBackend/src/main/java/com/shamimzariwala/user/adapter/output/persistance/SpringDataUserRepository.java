package com.shamimzariwala.user.adapter.output.persistance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository
        extends JpaRepository<UserEntity, Long> {
}