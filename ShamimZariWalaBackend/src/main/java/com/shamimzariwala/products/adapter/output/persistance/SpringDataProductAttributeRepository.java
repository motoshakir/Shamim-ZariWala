package com.shamimzariwala.products.adapter.output.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long> {
    Page<ProductAttributeEntity> findAllByProductId(Long productId, Pageable pageable);
}
