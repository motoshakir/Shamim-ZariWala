package com.shamimzariwala.products.domain.exception;

public class ProductAttributeNotFoundException extends RuntimeException {
    public ProductAttributeNotFoundException(Long id) {
        super("Product attribute with ID " + id + " was not found.");
    }
}
