package com.shamimzariwala.user.adapter.input.rest;
public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName
) {}
