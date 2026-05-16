package com.shamimzariwala.products.adapter.input.rest;

import com.shamimzariwala.products.adapter.output.persistance.ProductEntity;
import com.shamimzariwala.products.application.command.CreateProductCommand;
import com.shamimzariwala.products.application.command.UpdateProductCommand;
import com.shamimzariwala.products.domain.model.Product;

public class ProductMapper {

    public static CreateProductCommand toCommand(CreateProductRequest request) {
        return new CreateProductCommand(
                request.name(),
                request.description()
        );
    }

    public static UpdateProductCommand toCommand(Long productId, UpdateProductRequest request) {
        return new UpdateProductCommand(
                productId,
                request.name(),
                request.description(),
                request.status()
        );
    }

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return Product.restore(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;

        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
