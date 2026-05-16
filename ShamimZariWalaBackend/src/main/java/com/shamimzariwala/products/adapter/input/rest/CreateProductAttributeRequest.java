package com.shamimzariwala.products.adapter.input.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProductAttributeRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 64, message = "Name must be at most 64 characters")
        String name,

        @NotBlank(message = "Value is required")
        @Size(max = 255, message = "Value must be at most 255 characters")
        String value
) {}
