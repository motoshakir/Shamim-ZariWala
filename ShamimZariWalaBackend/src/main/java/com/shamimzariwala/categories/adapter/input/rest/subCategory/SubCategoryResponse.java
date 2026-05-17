package com.shamimzariwala.categories.adapter.input.rest.subCategory;

import java.time.LocalDateTime;

public record SubCategoryResponse(
        Long id,
        Long categoryId,
        String name,
        String description,
        LocalDateTime createdAt
) {}
