package com.shamimzariwala.categories.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shamimzariwala.categories.application.category.port.output.CategoryRepository;
import com.shamimzariwala.categories.application.category.service.CategoryService;
import com.shamimzariwala.categories.application.subCategory.port.output.SubCategoryRepository;
import com.shamimzariwala.categories.application.subCategory.service.SubCategoryService;

@Configuration
public class CategoryConfig {

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository,
                                           SubCategoryRepository subCategoryRepository) {
        return new CategoryService(categoryRepository, subCategoryRepository);
    }

    @Bean
    public SubCategoryService subCategoryService(SubCategoryRepository subCategoryRepository,
                                                 CategoryRepository categoryRepository) {
        return new SubCategoryService(subCategoryRepository, categoryRepository);
    }
}
