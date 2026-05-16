package com.shamimzariwala.products.adapter.input.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.shamimzariwala.products.domain.model.ProductStatus;

public record ProductVariantResponse(
        Long id,
        Long productId,
        String sku,
        BigDecimal price,
        int quantity,
        String imageUrl,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
