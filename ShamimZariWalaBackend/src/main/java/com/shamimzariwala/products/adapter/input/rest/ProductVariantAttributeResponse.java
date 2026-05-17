package com.shamimzariwala.products.adapter.input.rest;

import java.time.LocalDateTime;

public record ProductVariantAttributeResponse(
        Long id,
        Long variantId,
        String name,
        String value,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
