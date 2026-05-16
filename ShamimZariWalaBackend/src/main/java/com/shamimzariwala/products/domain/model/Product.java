package com.shamimzariwala.products.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String description;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Product() {}

    private Product(String name, String description, ProductStatus status) {
        this.name = Objects.requireNonNull(name, "Name is required");
        this.description = description;
        this.status = Objects.requireNonNull(status, "Status is required");
    }

    public static Product create(String name, String description) {
        return new Product(name, description, ProductStatus.ACTIVE);
    }

    public static Product restore(
            Long id,
            String name,
            String description,
            ProductStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        Product product = new Product();
        product.id = id;
        product.name = name;
        product.description = description;
        product.status = status;
        product.createdAt = createdAt;
        product.updatedAt = updatedAt;

        return product;
    }

    public void updateDetails(String name, String description) {
        if (name != null && !name.isBlank()) this.name = name;
        if (description != null) this.description = description;
    }

    public void changeStatus(ProductStatus status) {
        this.status = Objects.requireNonNull(status, "Status cannot be null");
    }

    public void activate() {
        this.status = ProductStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
    }

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}