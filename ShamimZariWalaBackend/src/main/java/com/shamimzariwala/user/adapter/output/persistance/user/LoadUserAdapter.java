package com.shamimzariwala.user.adapter.output.persistance.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shamimzariwala.auth.application.port.output.LoadUser;
import com.shamimzariwala.auth.domain.AuthUser;
import com.shamimzariwala.user.adapter.input.rest.user.UserMapper;

@Component
public class LoadUserAdapter implements LoadUser {

    private final SpringDataUserRepository repository;

    public LoadUserAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(UserMapper::toDomain)
                .map(UserMapper::toAuthUserDTO);
    }
}
