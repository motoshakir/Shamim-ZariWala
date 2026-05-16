package com.shamimzariwala.products.adapter.input.rest;

import com.shamimzariwala.products.adapter.output.persistance.ProductAttributeEntity;
import com.shamimzariwala.products.application.command.CreateProductAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductAttribute;

public class ProductAttributeMapper {

    public static CreateProductAttributeCommand toCommand(Long productId, CreateProductAttributeRequest request) {
        return new CreateProductAttributeCommand(productId, request.name(), request.value());
    }

    public static UpdateProductAttributeCommand toCommand(Long attributeId, UpdateProductAttributeRequest request) {
        return new UpdateProductAttributeCommand(attributeId, request.name(), request.value());
    }

    public static ProductAttribute toDomain(ProductAttributeEntity entity) {
        if (entity == null) return null;
        return ProductAttribute.restore(
                entity.getId(),
                entity.getProductId(),
                entity.getName(),
                entity.getValue(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ProductAttributeEntity toEntity(ProductAttribute attribute) {
        if (attribute == null) return null;
        return ProductAttributeEntity.builder()
                .id(attribute.getId())
                .productId(attribute.getProductId())
                .name(attribute.getName())
                .value(attribute.getValue())
                .createdAt(attribute.getCreatedAt())
                .updatedAt(attribute.getUpdatedAt())
                .build();
    }

    public static ProductAttributeResponse toResponse(ProductAttribute attribute) {
        return new ProductAttributeResponse(
                attribute.getId(),
                attribute.getProductId(),
                attribute.getName(),
                attribute.getValue(),
                attribute.getCreatedAt(),
                attribute.getUpdatedAt()
        );
    }
}
