package com.shamimzariwala.user.application.address.command;

public record UpdateAddressCommand(
        Long addressId,
        String title,
        String addressLine1,
        String addressLine2,
        String country,
        String city,
        String postalCode,
        String phoneNumber
) {}
