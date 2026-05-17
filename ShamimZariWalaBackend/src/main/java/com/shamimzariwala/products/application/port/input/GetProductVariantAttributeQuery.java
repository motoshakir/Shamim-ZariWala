package com.shamimzariwala.products.application.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public interface GetProductVariantAttributeQuery {
    Optional<ProductVariantAttribute> findById(Long attributeId);
    Page<ProductVariantAttribute> findAllByVariantId(Long variantId, Pageable pageable);
}
