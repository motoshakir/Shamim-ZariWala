package com.shamimzariwala.products.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductVariant {

    private Long id;
    private Long productId;
    private String sku;
    private BigDecimal price;
    private int quantity;
    private String imageUrl;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected ProductVariant() {}

    private ProductVariant(Long productId, String sku, BigDecimal price, int quantity, ProductStatus status) {
        this.productId = Objects.requireNonNull(productId, "Product id is required");
        this.sku = Objects.requireNonNull(sku, "SKU is required");
        setPrice(price);
        setQuantity(quantity);
        this.status = Objects.requireNonNull(status, "Status is required");
    }

    public static ProductVariant create(Long productId, String sku, BigDecimal price, int quantity, String imageUrl) {
        ProductVariant variant = new ProductVariant(productId, sku, price, quantity, ProductStatus.ACTIVE);
        variant.imageUrl = imageUrl;
        return variant;
    }

    public static ProductVariant restore(
            Long id,
            Long productId,
            String sku,
            BigDecimal price,
            int quantity,
            String imageUrl,
            ProductStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        ProductVariant variant = new ProductVariant();
        variant.id = id;
        variant.productId = productId;
        variant.sku = sku;
        variant.price = price;
        variant.quantity = quantity;
        variant.imageUrl = imageUrl;
        variant.status = status;
        variant.createdAt = createdAt;
        variant.updatedAt = updatedAt;
        return variant;
    }

    public void changePrice(BigDecimal newPrice) {
        setPrice(newPrice);
    }

    public void changeImage(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }

    public void setStock(int newQuantity) {
        setQuantity(newQuantity);
    }

    public void increaseStock(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Increase amount must be positive");
        this.quantity += amount;
    }

    public void decreaseStock(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Decrease amount must be positive");
        if (amount > this.quantity) throw new IllegalStateException("Insufficient stock for SKU " + sku);
        this.quantity -= amount;
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

    private void setPrice(BigDecimal price) {
        Objects.requireNonNull(price, "Price is required");
        if (price.signum() < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    private void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }

    // ---------------- GETTERS ----------------

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getSku() { return sku; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImageUrl() { return imageUrl; }
    public ProductStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
