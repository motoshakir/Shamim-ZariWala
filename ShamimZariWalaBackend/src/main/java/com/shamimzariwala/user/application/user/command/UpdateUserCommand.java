package com.shamimzariwala.user.application.user.command;

import java.time.LocalDate;

import com.shamimzariwala.user.domain.user.UserRole;
import com.shamimzariwala.user.domain.user.UserStatus;

public record UpdateUserCommand(
        Long userId,
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String avatar,
        String gender,
        LocalDate dateOfBirth,
        UserRole role,
        UserStatus status
) {}
