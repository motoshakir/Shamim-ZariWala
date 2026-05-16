package com.shamimzariwala.products.domain.exception;

public class ProductVariantNotFoundException extends RuntimeException {
    public ProductVariantNotFoundException(Long id) {
        super("Product variant with ID " + id + " was not found.");
    }
}
