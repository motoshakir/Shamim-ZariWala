package com.shamimzariwala.products.adapter.input.rest;

import com.shamimzariwala.products.adapter.output.persistance.ProductVariantEntity;
import com.shamimzariwala.products.application.command.CreateProductVariantCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantCommand;
import com.shamimzariwala.products.domain.model.ProductVariant;

public class ProductVariantMapper {

    public static CreateProductVariantCommand toCommand(Long productId, CreateProductVariantRequest request) {
        return new CreateProductVariantCommand(
                productId,
                request.sku(),
                request.price(),
                request.quantity(),
                request.imageUrl()
        );
    }

    public static UpdateProductVariantCommand toCommand(Long variantId, UpdateProductVariantRequest request) {
        return new UpdateProductVariantCommand(
                variantId,
                request.price(),
                request.quantity(),
                request.imageUrl(),
                request.status()
        );
    }

    public static ProductVariant toDomain(ProductVariantEntity entity) {
        if (entity == null) return null;
        return ProductVariant.restore(
                entity.getId(),
                entity.getProductId(),
                entity.getSku(),
                entity.getPrice(),
                entity.getQuantity(),
                entity.getImageUrl(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ProductVariantEntity toEntity(ProductVariant variant) {
        if (variant == null) return null;
        return ProductVariantEntity.builder()
                .id(variant.getId())
                .productId(variant.getProductId())
                .sku(variant.getSku())
                .price(variant.getPrice())
                .quantity(variant.getQuantity())
                .imageUrl(variant.getImageUrl())
                .status(variant.getStatus())
                .createdAt(variant.getCreatedAt())
                .updatedAt(variant.getUpdatedAt())
                .build();
    }

    public static ProductVariantResponse toResponse(ProductVariant variant) {
        return new ProductVariantResponse(
                variant.getId(),
                variant.getProductId(),
                variant.getSku(),
                variant.getPrice(),
                variant.getQuantity(),
                variant.getImageUrl(),
                variant.getStatus(),
                variant.getCreatedAt(),
                variant.getUpdatedAt()
        );
    }
}
