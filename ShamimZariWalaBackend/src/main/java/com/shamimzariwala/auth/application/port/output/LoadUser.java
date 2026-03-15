package com.shamimzariwala.auth.application.port.output;

import java.util.Optional;

import com.shamimzariwala.auth.domain.AuthUser;

public interface LoadUser {
    Optional<AuthUser> findByEmail(String email);
}
