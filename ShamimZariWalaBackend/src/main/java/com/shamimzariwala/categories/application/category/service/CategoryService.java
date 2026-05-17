package com.shamimzariwala.categories.application.category.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.categories.application.category.command.CreateCategoryCommand;
import com.shamimzariwala.categories.application.category.command.UpdateCategoryCommand;
import com.shamimzariwala.categories.application.category.port.input.CreateCategoryUseCase;
import com.shamimzariwala.categories.application.category.port.input.DeleteCategoryUseCase;
import com.shamimzariwala.categories.application.category.port.input.GetCategoryQuery;
import com.shamimzariwala.categories.application.category.port.input.UpdateCategoryUseCase;
import com.shamimzariwala.categories.application.category.port.output.CategoryRepository;
import com.shamimzariwala.categories.application.subCategory.port.output.SubCategoryRepository;
import com.shamimzariwala.categories.domain.category.Category;
import com.shamimzariwala.categories.domain.category.CategoryAlreadyExistsException;
import com.shamimzariwala.categories.domain.category.CategoryNotFoundException;

public class CategoryService implements
        CreateCategoryUseCase, UpdateCategoryUseCase, GetCategoryQuery, DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public Category createCategory(CreateCategoryCommand command) {
        categoryRepository.findByName(command.name()).ifPresent(c -> {
            throw new CategoryAlreadyExistsException(command.name());
        });

        Category category = Category.create(command.name(), command.description());
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(UpdateCategoryCommand command) {
        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(command.categoryId()));

        category.update(command.name(), command.description());
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteById(Long categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }
        subCategoryRepository.deleteAllByCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
