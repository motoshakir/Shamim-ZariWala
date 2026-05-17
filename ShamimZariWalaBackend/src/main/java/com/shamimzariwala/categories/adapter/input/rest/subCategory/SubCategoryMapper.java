package com.shamimzariwala.categories.adapter.input.rest.subCategory;

import com.shamimzariwala.categories.adapter.output.persistance.subCategory.SubCategoryEntity;
import com.shamimzariwala.categories.application.subCategory.command.CreateSubCategoryCommand;
import com.shamimzariwala.categories.application.subCategory.command.UpdateSubCategoryCommand;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;

public class SubCategoryMapper {

    public static CreateSubCategoryCommand toCommand(Long categoryId, CreateSubCategoryRequest request) {
        return new CreateSubCategoryCommand(categoryId, request.name(), request.description());
    }

    public static UpdateSubCategoryCommand toCommand(Long subCategoryId, UpdateSubCategoryRequest request) {
        return new UpdateSubCategoryCommand(subCategoryId, request.name(), request.description());
    }

    public static SubCategory toDomain(SubCategoryEntity entity) {
        if (entity == null) return null;
        return SubCategory.restore(
                entity.getId(),
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt());
    }

    public static SubCategoryEntity toEntity(SubCategory subCategory) {
        if (subCategory == null) return null;
        return SubCategoryEntity.builder()
                .id(subCategory.getId())
                .categoryId(subCategory.getCategoryId())
                .name(subCategory.getName())
                .description(subCategory.getDescription())
                .createdAt(subCategory.getCreatedAt())
                .build();
    }

    public static SubCategoryResponse toResponse(SubCategory subCategory) {
        return new SubCategoryResponse(
                subCategory.getId(),
                subCategory.getCategoryId(),
                subCategory.getName(),
                subCategory.getDescription(),
                subCategory.getCreatedAt());
    }
}
