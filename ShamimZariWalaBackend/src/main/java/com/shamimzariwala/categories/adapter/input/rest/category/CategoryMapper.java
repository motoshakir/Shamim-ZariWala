package com.shamimzariwala.categories.adapter.input.rest.category;

import com.shamimzariwala.categories.adapter.output.persistance.category.CategoryEntity;
import com.shamimzariwala.categories.application.category.command.CreateCategoryCommand;
import com.shamimzariwala.categories.application.category.command.UpdateCategoryCommand;
import com.shamimzariwala.categories.domain.category.Category;

public class CategoryMapper {

    public static CreateCategoryCommand toCommand(CreateCategoryRequest request) {
        return new CreateCategoryCommand(request.name(), request.description());
    }

    public static UpdateCategoryCommand toCommand(Long categoryId, UpdateCategoryRequest request) {
        return new UpdateCategoryCommand(categoryId, request.name(), request.description());
    }

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        return Category.restore(entity.getId(), entity.getName(), entity.getDescription(), entity.getCreatedAt());
    }

    public static CategoryEntity toEntity(Category category) {
        if (category == null) return null;
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .build();
    }

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription(), category.getCreatedAt());
    }
}
