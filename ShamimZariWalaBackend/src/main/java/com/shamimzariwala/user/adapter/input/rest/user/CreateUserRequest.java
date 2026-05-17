package com.shamimzariwala.user.adapter.input.rest.user;

import java.time.LocalDate;

public record CreateUserRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String avatar,
        String gender,
        LocalDate dateOfBirth
) {}
