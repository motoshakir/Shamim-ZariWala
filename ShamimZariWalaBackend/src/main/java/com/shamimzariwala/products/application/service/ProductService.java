package com.shamimzariwala.products.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.products.application.command.CreateProductCommand;
import com.shamimzariwala.products.application.command.UpdateProductCommand;
import com.shamimzariwala.products.application.port.input.CreateProductUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductUseCase;
import com.shamimzariwala.products.application.port.input.GetProductQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductUseCase;
import com.shamimzariwala.products.application.port.output.ProductRepository;
import com.shamimzariwala.products.domain.exception.ProductAlreadyExistsException;
import com.shamimzariwala.products.domain.exception.ProductNotFoundException;
import com.shamimzariwala.products.domain.model.Product;

public class ProductService implements CreateProductUseCase, UpdateProductUseCase, GetProductQuery, DeleteProductUseCase {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        productRepository.findByName(command.name()).ifPresent(p -> {
            throw new ProductAlreadyExistsException(command.name());
        });

        Product product = Product.create(command.name(), command.description());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(UpdateProductCommand command) {
        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException(command.productId()));

        product.updateDetails(command.name(), command.description());

        if (command.status() != null) {
            product.changeStatus(command.status());
        }

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
