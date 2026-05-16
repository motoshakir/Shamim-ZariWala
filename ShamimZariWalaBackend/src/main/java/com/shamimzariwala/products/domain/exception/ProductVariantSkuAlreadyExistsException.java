package com.shamimzariwala.products.domain.exception;

public class ProductVariantSkuAlreadyExistsException extends RuntimeException {
    public ProductVariantSkuAlreadyExistsException(String sku) {
        super("Product variant with SKU '" + sku + "' already exists.");
    }
}
