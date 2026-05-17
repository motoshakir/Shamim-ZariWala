package com.shamimzariwala.products.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductVariantAttribute {

    private Long id;
    private Long variantId;
    private String name;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected ProductVariantAttribute() {}

    private ProductVariantAttribute(Long variantId, String name, String value) {
        this.variantId = Objects.requireNonNull(variantId, "Variant id is required");
        this.name = requireNonBlank(name, "Name is required");
        this.value = requireNonBlank(value, "Value is required");
    }

    public static ProductVariantAttribute create(Long variantId, String name, String value) {
        return new ProductVariantAttribute(variantId, name, value);
    }

    public static ProductVariantAttribute restore(
            Long id,
            Long variantId,
            String name,
            String value,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        ProductVariantAttribute attribute = new ProductVariantAttribute();
        attribute.id = id;
        attribute.variantId = variantId;
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
    public Long getVariantId() { return variantId; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
