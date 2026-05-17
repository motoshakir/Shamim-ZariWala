package com.shamimzariwala.products.application.command;

public record UpdateProductVariantAttributeCommand(
        Long attributeId,
        String name,
        String value
) {}
