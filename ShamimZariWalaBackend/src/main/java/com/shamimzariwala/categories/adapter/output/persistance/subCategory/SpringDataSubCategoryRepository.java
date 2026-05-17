package com.shamimzariwala.categories.adapter.output.persistance.subCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataSubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    Page<SubCategoryEntity> findAllByCategoryId(Long categoryId, Pageable pageable);
    void deleteAllByCategoryId(Long categoryId);
}
