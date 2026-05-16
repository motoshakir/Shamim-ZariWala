package com.shamimzariwala.products.adapter.output.persistance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductVariantRepository extends JpaRepository<ProductVariantEntity, Long> {
    Optional<ProductVariantEntity> findBySku(String sku);
    Page<ProductVariantEntity> findAllByProductId(Long productId, Pageable pageable);
}
