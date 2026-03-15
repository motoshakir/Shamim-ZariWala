package com.shamimzariwala.auth.domain;


import com.shamimzariwala.user.domain.model.UserRole;


public record AuthUser(
    String email,
    String password,
    UserRole roles
) {}
