package com.shamimzariwala.products.adapter.input.rest;

import java.time.LocalDateTime;

import com.shamimzariwala.products.domain.model.ProductStatus;

public record ProductResponse(
        Long id,
        String name,
        String description,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
