package com.shamimzariwala.categories.adapter.output.persistance.subCategory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.categories.adapter.input.rest.subCategory.SubCategoryMapper;
import com.shamimzariwala.categories.application.subCategory.port.output.SubCategoryRepository;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;
import com.shamimzariwala.categories.domain.subCategory.SubCategoryNotFoundException;

@Repository
public class SubCategoryPersistenceAdapter implements SubCategoryRepository {

    private final SpringDataSubCategoryRepository repository;

    public SubCategoryPersistenceAdapter(SpringDataSubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        SubCategoryEntity entity = SubCategoryMapper.toEntity(subCategory);
        SubCategoryEntity saved = repository.save(entity);
        return SubCategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<SubCategory> findById(Long subCategoryId) {
        return repository.findById(subCategoryId).map(SubCategoryMapper::toDomain);
    }

    @Override
    public Page<SubCategory> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return repository.findAllByCategoryId(categoryId, pageable).map(SubCategoryMapper::toDomain);
    }

    @Override
    public void deleteById(Long subCategoryId) {
        if (!repository.findById(subCategoryId).isPresent()) {
            throw new SubCategoryNotFoundException(subCategoryId);
        }
        repository.deleteById(subCategoryId);
    }

    @Override
    @Transactional
    public void deleteAllByCategoryId(Long categoryId) {
        repository.deleteAllByCategoryId(categoryId);
    }
}
