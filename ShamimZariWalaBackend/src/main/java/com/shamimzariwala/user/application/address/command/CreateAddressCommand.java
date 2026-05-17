package com.shamimzariwala.user.application.address.command;

public record CreateAddressCommand(
        Long userId,
        String title,
        String addressLine1,
        String addressLine2,
        String country,
        String city,
        String postalCode,
        String phoneNumber
) {}
