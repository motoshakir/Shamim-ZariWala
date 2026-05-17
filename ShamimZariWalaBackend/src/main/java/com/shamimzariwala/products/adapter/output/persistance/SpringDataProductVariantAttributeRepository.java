package com.shamimzariwala.products.adapter.output.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductVariantAttributeRepository extends JpaRepository<ProductVariantAttributeEntity, Long> {
    Page<ProductVariantAttributeEntity> findAllByVariantId(Long variantId, Pageable pageable);
}
