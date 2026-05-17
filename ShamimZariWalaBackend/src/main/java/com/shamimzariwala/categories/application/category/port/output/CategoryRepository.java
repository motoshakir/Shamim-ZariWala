package com.shamimzariwala.categories.application.category.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.categories.domain.category.Category;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long categoryId);
    Optional<Category> findByName(String name);
    Page<Category> findAll(Pageable pageable);
    void deleteById(Long categoryId);
}
