package com.shamimzariwala.categories.application.subCategory.command;

public record CreateSubCategoryCommand(
        Long categoryId,
        String name,
        String description
) {}
