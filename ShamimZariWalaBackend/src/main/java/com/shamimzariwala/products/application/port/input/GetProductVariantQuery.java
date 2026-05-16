package com.shamimzariwala.products.application.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductVariant;

public interface GetProductVariantQuery {
    Optional<ProductVariant> findById(Long variantId);
    Page<ProductVariant> findAllByProductId(Long productId, Pageable pageable);
}
