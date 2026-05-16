package com.shamimzariwala.products.adapter.output.persistance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.products.adapter.input.rest.ProductVariantMapper;
import com.shamimzariwala.products.application.port.output.ProductVariantRepository;
import com.shamimzariwala.products.domain.exception.ProductVariantNotFoundException;
import com.shamimzariwala.products.domain.exception.ProductVariantSkuAlreadyExistsException;
import com.shamimzariwala.products.domain.model.ProductVariant;

@Repository
public class ProductVariantPersistenceAdapter implements ProductVariantRepository {

    private final SpringDataProductVariantRepository repository;

    public ProductVariantPersistenceAdapter(SpringDataProductVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductVariant save(ProductVariant variant) {
        repository.findBySku(variant.getSku())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(variant.getId())) {
                        throw new ProductVariantSkuAlreadyExistsException(variant.getSku());
                    }
                });

        ProductVariantEntity entity = ProductVariantMapper.toEntity(variant);
        ProductVariantEntity saved = repository.save(entity);
        return ProductVariantMapper.toDomain(saved);
    }

    @Override
    public Optional<ProductVariant> findById(Long variantId) {
        return repository.findById(variantId).map(ProductVariantMapper::toDomain);
    }

    @Override
    public Optional<ProductVariant> findBySku(String sku) {
        return repository.findBySku(sku).map(ProductVariantMapper::toDomain);
    }

    @Override
    public Page<ProductVariant> findAllByProductId(Long productId, Pageable pageable) {
        return repository.findAllByProductId(productId, pageable).map(ProductVariantMapper::toDomain);
    }

    @Override
    public void deleteById(Long variantId) {
        if (!repository.findById(variantId).isPresent()) {
            throw new ProductVariantNotFoundException(variantId);
        }
        repository.deleteById(variantId);
    }
}
