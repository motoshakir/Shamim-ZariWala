package com.shamimzariwala.products.adapter.input.rest;

import java.math.BigDecimal;

import com.shamimzariwala.products.domain.model.ProductStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProductVariantRequest(
        @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
        BigDecimal price,

        @PositiveOrZero(message = "Quantity cannot be negative")
        Integer quantity,

        String imageUrl,

        ProductStatus status
) {}
