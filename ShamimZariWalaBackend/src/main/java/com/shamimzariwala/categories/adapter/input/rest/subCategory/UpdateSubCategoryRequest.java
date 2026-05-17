package com.shamimzariwala.categories.adapter.input.rest.subCategory;

import jakarta.validation.constraints.Size;

public record UpdateSubCategoryRequest(
        @Size(max = 64, message = "Name must be at most 64 characters")
        String name,

        @Size(max = 1000, message = "Description must be at most 1000 characters")
        String description
) {}
