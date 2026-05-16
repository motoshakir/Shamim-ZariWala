package com.shamimzariwala.products.application.command;

public record UpdateProductAttributeCommand(
        Long attributeId,
        String name,
        String value
) {}
