package com.shamimzariwala.products.domain.exception;

public class ProductVariantAttributeNotFoundException extends RuntimeException {
    public ProductVariantAttributeNotFoundException(Long id) {
        super("Product variant attribute with ID " + id + " was not found.");
    }
}
