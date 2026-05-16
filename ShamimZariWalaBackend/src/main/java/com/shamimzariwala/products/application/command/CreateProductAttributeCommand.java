package com.shamimzariwala.products.application.command;

public record CreateProductAttributeCommand(
        Long productId,
        String name,
        String value
) {}
