package com.shamimzariwala.products.adapter.output.persistance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.products.adapter.input.rest.ProductAttributeMapper;
import com.shamimzariwala.products.application.port.output.ProductAttributeRepository;
import com.shamimzariwala.products.domain.exception.ProductAttributeNotFoundException;
import com.shamimzariwala.products.domain.model.ProductAttribute;

@Repository
public class ProductAttributePersistenceAdapter implements ProductAttributeRepository {

    private final SpringDataProductAttributeRepository repository;

    public ProductAttributePersistenceAdapter(SpringDataProductAttributeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductAttribute save(ProductAttribute attribute) {
        ProductAttributeEntity entity = ProductAttributeMapper.toEntity(attribute);
        ProductAttributeEntity saved = repository.save(entity);
        return ProductAttributeMapper.toDomain(saved);
    }

    @Override
    public Optional<ProductAttribute> findById(Long attributeId) {
        return repository.findById(attributeId).map(ProductAttributeMapper::toDomain);
    }

    @Override
    public Page<ProductAttribute> findAllByProductId(Long productId, Pageable pageable) {
        return repository.findAllByProductId(productId, pageable).map(ProductAttributeMapper::toDomain);
    }

    @Override
    public void deleteById(Long attributeId) {
        if (!repository.findById(attributeId).isPresent()) {
            throw new ProductAttributeNotFoundException(attributeId);
        }
        repository.deleteById(attributeId);
    }
}
