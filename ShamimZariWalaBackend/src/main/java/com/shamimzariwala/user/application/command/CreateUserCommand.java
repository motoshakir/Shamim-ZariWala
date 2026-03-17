package com.shamimzariwala.user.application.command;

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
