package com.shamimzariwala.products.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductAttribute {

    private Long id;
    private Long productId;
    private String name;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected ProductAttribute() {}

    private ProductAttribute(Long productId, String name, String value) {
        this.productId = Objects.requireNonNull(productId, "Product id is required");
        this.name = requireNonBlank(name, "Name is required");
        this.value = requireNonBlank(value, "Value is required");
    }

    public static ProductAttribute create(Long productId, String name, String value) {
        return new ProductAttribute(productId, name, value);
    }

    public static ProductAttribute restore(
            Long id,
            Long productId,
            String name,
            String value,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        ProductAttribute attribute = new ProductAttribute();
        attribute.id = id;
        attribute.productId = productId;
        attribute.name = name;
        attribute.value = value;
        attribute.createdAt = createdAt;
        attribute.updatedAt = updatedAt;
        return attribute;
    }

    public void update(String name, String value) {
        if (name != null && !name.isBlank()) this.name = name;
        if (value != null && !value.isBlank()) this.value = value;
    }

    private static String requireNonBlank(String s, String message) {
        if (s == null || s.isBlank()) throw new IllegalArgumentException(message);
        return s;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
