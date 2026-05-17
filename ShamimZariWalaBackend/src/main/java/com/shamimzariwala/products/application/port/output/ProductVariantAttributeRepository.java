package com.shamimzariwala.products.application.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public interface ProductVariantAttributeRepository {
    ProductVariantAttribute save(ProductVariantAttribute attribute);
    Optional<ProductVariantAttribute> findById(Long attributeId);
    Page<ProductVariantAttribute> findAllByVariantId(Long variantId, Pageable pageable);
    void deleteById(Long attributeId);
}
