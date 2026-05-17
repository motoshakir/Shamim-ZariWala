package com.shamimzariwala.categories.application.subCategory.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.categories.domain.subCategory.SubCategory;

public interface GetSubCategoryQuery {
    Optional<SubCategory> findById(Long subCategoryId);
    Page<SubCategory> findAllByCategoryId(Long categoryId, Pageable pageable);
}
