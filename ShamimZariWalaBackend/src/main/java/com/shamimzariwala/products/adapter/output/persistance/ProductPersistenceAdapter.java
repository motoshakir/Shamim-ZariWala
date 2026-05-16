package com.shamimzariwala.products.adapter.output.persistance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.products.adapter.input.rest.ProductMapper;
import com.shamimzariwala.products.application.port.output.ProductRepository;
import com.shamimzariwala.products.domain.exception.ProductAlreadyExistsException;
import com.shamimzariwala.products.domain.exception.ProductNotFoundException;
import com.shamimzariwala.products.domain.model.Product;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;

    public ProductPersistenceAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {

        repository.findByName(product.getName())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(product.getId())) {
                        throw new ProductAlreadyExistsException(product.getName());
                    }
                });

        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = repository.save(entity);

        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return repository.findById(productId)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return repository.findByName(name)
                .map(ProductMapper::toDomain);
    }

    @Override
    public void deleteById(Long productId) {
        if (!repository.findById(productId).isPresent()) {
            throw new ProductNotFoundException(productId);
        }
        repository.deleteById(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ProductMapper::toDomain);
    }
}
