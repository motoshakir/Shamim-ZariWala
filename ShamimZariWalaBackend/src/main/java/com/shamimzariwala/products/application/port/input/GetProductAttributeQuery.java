package com.shamimzariwala.products.application.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductAttribute;

public interface GetProductAttributeQuery {
    Optional<ProductAttribute> findById(Long attributeId);
    Page<ProductAttribute> findAllByProductId(Long productId, Pageable pageable);
}
