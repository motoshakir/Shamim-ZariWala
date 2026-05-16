package com.shamimzariwala.products.adapter.input.rest;

import java.time.LocalDateTime;

public record ProductAttributeResponse(
        Long id,
        Long productId,
        String name,
        String value,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
