package com.shamimzariwala.user.application.command;

public record CreateUserCommand(
    String email,
    String password
) {}
