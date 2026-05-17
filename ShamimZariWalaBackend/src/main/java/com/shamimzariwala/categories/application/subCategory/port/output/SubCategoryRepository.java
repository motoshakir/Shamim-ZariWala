package com.shamimzariwala.categories.application.subCategory.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.categories.domain.subCategory.SubCategory;

public interface SubCategoryRepository {
    SubCategory save(SubCategory subCategory);
    Optional<SubCategory> findById(Long subCategoryId);
    Page<SubCategory> findAllByCategoryId(Long categoryId, Pageable pageable);
    void deleteById(Long subCategoryId);
    void deleteAllByCategoryId(Long categoryId);
}
