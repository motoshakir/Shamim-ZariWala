package com.shamimzariwala.user.adapter.input.rest.address;

import jakarta.validation.constraints.Size;

public record UpdateAddressRequest(
        @Size(max = 64, message = "Title must be at most 64 characters")
        String title,

        @Size(max = 255, message = "Address line 1 must be at most 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address line 2 must be at most 255 characters")
        String addressLine2,

        @Size(max = 64, message = "Country must be at most 64 characters")
        String country,

        @Size(max = 64, message = "City must be at most 64 characters")
        String city,

        @Size(max = 16, message = "Postal code must be at most 16 characters")
        String postalCode,

        @Size(max = 32, message = "Phone number must be at most 32 characters")
        String phoneNumber
) {}
