package com.shamimzariwala.products.application.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductVariant;

public interface ProductVariantRepository {
    ProductVariant save(ProductVariant variant);
    Optional<ProductVariant> findById(Long variantId);
    Optional<ProductVariant> findBySku(String sku);
    Page<ProductVariant> findAllByProductId(Long productId, Pageable pageable);
    void deleteById(Long variantId);
}
