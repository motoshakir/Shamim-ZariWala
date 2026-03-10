package com.shamimzariwala.user.adapter.input.rest;

public record CreateUserRequest(
        String email,
        String password
) {}