package com.shamimzariwala.products.adapter.input.rest;

import com.shamimzariwala.products.adapter.output.persistance.ProductVariantAttributeEntity;
import com.shamimzariwala.products.application.command.CreateProductVariantAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public class ProductVariantAttributeMapper {

    public static CreateProductVariantAttributeCommand toCommand(Long variantId, CreateProductVariantAttributeRequest request) {
        return new CreateProductVariantAttributeCommand(variantId, request.name(), request.value());
    }

    public static UpdateProductVariantAttributeCommand toCommand(Long attributeId, UpdateProductVariantAttributeRequest request) {
        return new UpdateProductVariantAttributeCommand(attributeId, request.name(), request.value());
    }

    public static ProductVariantAttribute toDomain(ProductVariantAttributeEntity entity) {
        if (entity == null) return null;
        return ProductVariantAttribute.restore(
                entity.getId(),
                entity.getVariantId(),
                entity.getName(),
                entity.getValue(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ProductVariantAttributeEntity toEntity(ProductVariantAttribute attribute) {
        if (attribute == null) return null;
        return ProductVariantAttributeEntity.builder()
                .id(attribute.getId())
                .variantId(attribute.getVariantId())
                .name(attribute.getName())
                .value(attribute.getValue())
                .createdAt(attribute.getCreatedAt())
                .updatedAt(attribute.getUpdatedAt())
                .build();
    }

    public static ProductVariantAttributeResponse toResponse(ProductVariantAttribute attribute) {
        return new ProductVariantAttributeResponse(
                attribute.getId(),
                attribute.getVariantId(),
                attribute.getName(),
                attribute.getValue(),
                attribute.getCreatedAt(),
                attribute.getUpdatedAt()
        );
    }
}
