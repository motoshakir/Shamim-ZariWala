package com.shamimzariwala.products.application.command;

import com.shamimzariwala.products.domain.model.ProductStatus;

public record UpdateProductCommand(
        Long productId,
        String name,
        String description,
        ProductStatus status
) {}
