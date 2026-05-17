package com.shamimzariwala.products.adapter.input.rest;

import jakarta.validation.constraints.Size;

public record UpdateProductVariantAttributeRequest(
        @Size(max = 64, message = "Name must be at most 64 characters")
        String name,

        @Size(max = 255, message = "Value must be at most 255 characters")
        String value
) {}
