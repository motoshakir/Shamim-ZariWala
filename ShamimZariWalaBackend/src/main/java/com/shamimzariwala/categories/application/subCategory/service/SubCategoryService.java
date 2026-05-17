package com.shamimzariwala.categories.application.subCategory.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.categories.application.category.port.output.CategoryRepository;
import com.shamimzariwala.categories.application.subCategory.command.CreateSubCategoryCommand;
import com.shamimzariwala.categories.application.subCategory.command.UpdateSubCategoryCommand;
import com.shamimzariwala.categories.application.subCategory.port.input.CreateSubCategoryUseCase;
import com.shamimzariwala.categories.application.subCategory.port.input.DeleteSubCategoryUseCase;
import com.shamimzariwala.categories.application.subCategory.port.input.GetSubCategoryQuery;
import com.shamimzariwala.categories.application.subCategory.port.input.UpdateSubCategoryUseCase;
import com.shamimzariwala.categories.application.subCategory.port.output.SubCategoryRepository;
import com.shamimzariwala.categories.domain.category.CategoryNotFoundException;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;
import com.shamimzariwala.categories.domain.subCategory.SubCategoryNotFoundException;

public class SubCategoryService implements
        CreateSubCategoryUseCase, UpdateSubCategoryUseCase, GetSubCategoryQuery, DeleteSubCategoryUseCase {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubCategory createSubCategory(CreateSubCategoryCommand command) {
        categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(command.categoryId()));

        SubCategory subCategory = SubCategory.create(command.categoryId(), command.name(), command.description());
        return subCategoryRepository.save(subCategory);
    }

    @Override
    @Transactional
    public SubCategory update(UpdateSubCategoryCommand command) {
        SubCategory subCategory = subCategoryRepository.findById(command.subCategoryId())
                .orElseThrow(() -> new SubCategoryNotFoundException(command.subCategoryId()));

        subCategory.update(command.name(), command.description());
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public Optional<SubCategory> findById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId);
    }

    @Override
    public Page<SubCategory> findAllByCategoryId(Long categoryId, Pageable pageable) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return subCategoryRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public void deleteById(Long subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }
}
