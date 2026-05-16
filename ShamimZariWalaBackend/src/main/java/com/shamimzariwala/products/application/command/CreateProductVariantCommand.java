package com.shamimzariwala.products.application.command;

import java.math.BigDecimal;

public record CreateProductVariantCommand(
        Long productId,
        String sku,
        BigDecimal price,
        int quantity,
        String imageUrl
) {}
