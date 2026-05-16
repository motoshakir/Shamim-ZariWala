package com.shamimzariwala.products.application.command;

import java.math.BigDecimal;

import com.shamimzariwala.products.domain.model.ProductStatus;

public record UpdateProductVariantCommand(
        Long variantId,
        BigDecimal price,
        Integer quantity,
        String imageUrl,
        ProductStatus status
) {}
