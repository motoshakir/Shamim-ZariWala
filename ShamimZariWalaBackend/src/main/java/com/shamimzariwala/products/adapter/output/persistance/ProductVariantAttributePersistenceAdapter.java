package com.shamimzariwala.products.adapter.output.persistance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.products.adapter.input.rest.ProductVariantAttributeMapper;
import com.shamimzariwala.products.application.port.output.ProductVariantAttributeRepository;
import com.shamimzariwala.products.domain.exception.ProductVariantAttributeNotFoundException;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

@Repository
public class ProductVariantAttributePersistenceAdapter implements ProductVariantAttributeRepository {

    private final SpringDataProductVariantAttributeRepository repository;

    public ProductVariantAttributePersistenceAdapter(SpringDataProductVariantAttributeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductVariantAttribute save(ProductVariantAttribute attribute) {
        ProductVariantAttributeEntity entity = ProductVariantAttributeMapper.toEntity(attribute);
        ProductVariantAttributeEntity saved = repository.save(entity);
        return ProductVariantAttributeMapper.toDomain(saved);
    }

    @Override
    public Optional<ProductVariantAttribute> findById(Long attributeId) {
        return repository.findById(attributeId).map(ProductVariantAttributeMapper::toDomain);
    }

    @Override
    public Page<ProductVariantAttribute> findAllByVariantId(Long variantId, Pageable pageable) {
        return repository.findAllByVariantId(variantId, pageable).map(ProductVariantAttributeMapper::toDomain);
    }

    @Override
    public void deleteById(Long attributeId) {
        if (!repository.findById(attributeId).isPresent()) {
            throw new ProductVariantAttributeNotFoundException(attributeId);
        }
        repository.deleteById(attributeId);
    }
}
