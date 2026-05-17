package com.shamimzariwala.categories.application.category.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.categories.domain.category.Category;

public interface GetCategoryQuery {
    Optional<Category> findById(Long categoryId);
    Page<Category> findAll(Pageable pageable);
}
