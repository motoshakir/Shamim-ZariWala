package com.shamimzariwala.user.adapter.input.rest.user;

import java.time.LocalDate;

import com.shamimzariwala.user.domain.user.UserRole;
import com.shamimzariwala.user.domain.user.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Email(message = "Invalid email format")
    String email,

    @Size(min = 8, message = "Password must be at least 8 characters")
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
