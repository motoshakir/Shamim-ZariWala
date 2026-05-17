package com.shamimzariwala.user.adapter.input.rest.address;

import java.time.LocalDateTime;

public record AddressResponse(
        Long id,
        Long userId,
        String title,
        String addressLine1,
        String addressLine2,
        String country,
        String city,
        String postalCode,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
