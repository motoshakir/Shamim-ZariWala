package com.shamimzariwala.categories.application.subCategory.command;

public record UpdateSubCategoryCommand(
        Long subCategoryId,
        String name,
        String description
) {}
