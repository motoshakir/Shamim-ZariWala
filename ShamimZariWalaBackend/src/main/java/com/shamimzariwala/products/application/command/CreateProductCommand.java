package com.shamimzariwala.products.application.command;

public record CreateProductCommand(
        String name,
        String description
) {}
