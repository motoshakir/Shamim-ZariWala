package com.shamimzariwala.categories.application.category.command;

public record UpdateCategoryCommand(
        Long categoryId,
        String name,
        String description
) {}
