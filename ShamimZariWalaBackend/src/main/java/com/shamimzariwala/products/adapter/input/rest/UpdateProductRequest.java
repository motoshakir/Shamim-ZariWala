package com.shamimzariwala.products.adapter.input.rest;

import com.shamimzariwala.products.domain.model.ProductStatus;

import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @Size(max = 200, message = "Name must be at most 200 characters")
        String name,

        String description,

        ProductStatus status
) {}
