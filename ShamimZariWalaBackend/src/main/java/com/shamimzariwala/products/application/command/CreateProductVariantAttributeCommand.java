package com.shamimzariwala.products.application.command;

public record CreateProductVariantAttributeCommand(
        Long variantId,
        String name,
        String value
) {}
