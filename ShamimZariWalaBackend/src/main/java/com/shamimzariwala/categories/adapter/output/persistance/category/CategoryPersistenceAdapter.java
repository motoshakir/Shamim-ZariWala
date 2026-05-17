package com.shamimzariwala.categories.adapter.output.persistance.category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.categories.adapter.input.rest.category.CategoryMapper;
import com.shamimzariwala.categories.application.category.port.output.CategoryRepository;
import com.shamimzariwala.categories.domain.category.Category;
import com.shamimzariwala.categories.domain.category.CategoryAlreadyExistsException;
import com.shamimzariwala.categories.domain.category.CategoryNotFoundException;

@Repository
public class CategoryPersistenceAdapter implements CategoryRepository {

    private final SpringDataCategoryRepository repository;

    public CategoryPersistenceAdapter(SpringDataCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        repository.findByName(category.getName())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(category.getId())) {
                        throw new CategoryAlreadyExistsException(category.getName());
                    }
                });

        CategoryEntity entity = CategoryMapper.toEntity(category);
        CategoryEntity saved = repository.save(entity);
        return CategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return repository.findById(categoryId).map(CategoryMapper::toDomain);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name).map(CategoryMapper::toDomain);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(CategoryMapper::toDomain);
    }

    @Override
    public void deleteById(Long categoryId) {
        if (!repository.findById(categoryId).isPresent()) {
            throw new CategoryNotFoundException(categoryId);
        }
        repository.deleteById(categoryId);
    }
}
