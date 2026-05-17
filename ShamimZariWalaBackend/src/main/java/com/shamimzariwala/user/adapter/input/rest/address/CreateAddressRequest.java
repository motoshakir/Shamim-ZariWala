package com.shamimzariwala.user.adapter.input.rest.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAddressRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 64, message = "Title must be at most 64 characters")
        String title,

        @NotBlank(message = "Address line 1 is required")
        @Size(max = 255, message = "Address line 1 must be at most 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address line 2 must be at most 255 characters")
        String addressLine2,

        @NotBlank(message = "Country is required")
        @Size(max = 64, message = "Country must be at most 64 characters")
        String country,

        @NotBlank(message = "City is required")
        @Size(max = 64, message = "City must be at most 64 characters")
        String city,

        @NotBlank(message = "Postal code is required")
        @Size(max = 16, message = "Postal code must be at most 16 characters")
        String postalCode,

        @NotBlank(message = "Phone number is required")
        @Size(max = 32, message = "Phone number must be at most 32 characters")
        String phoneNumber
) {}
