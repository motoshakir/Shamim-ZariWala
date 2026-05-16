package com.shamimzariwala.products.application.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.ProductAttribute;

public interface ProductAttributeRepository {
    ProductAttribute save(ProductAttribute attribute);
    Optional<ProductAttribute> findById(Long attributeId);
    Page<ProductAttribute> findAllByProductId(Long productId, Pageable pageable);
    void deleteById(Long attributeId);
}
