package com.shamimzariwala.user.application.user.command;

import java.time.LocalDate;

public record CreateUserCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String avatar,
        String gender,
        LocalDate dateOfBirth
) {}
