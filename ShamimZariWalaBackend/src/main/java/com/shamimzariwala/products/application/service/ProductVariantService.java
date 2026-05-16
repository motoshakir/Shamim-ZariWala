package com.shamimzariwala.products.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.products.application.command.CreateProductVariantCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantCommand;
import com.shamimzariwala.products.application.port.input.CreateProductVariantUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductVariantUseCase;
import com.shamimzariwala.products.application.port.input.GetProductVariantQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductVariantUseCase;
import com.shamimzariwala.products.application.port.output.ProductRepository;
import com.shamimzariwala.products.application.port.output.ProductVariantRepository;
import com.shamimzariwala.products.domain.exception.ProductNotFoundException;
import com.shamimzariwala.products.domain.exception.ProductVariantNotFoundException;
import com.shamimzariwala.products.domain.exception.ProductVariantSkuAlreadyExistsException;
import com.shamimzariwala.products.domain.model.ProductVariant;

public class ProductVariantService implements
        CreateProductVariantUseCase, UpdateProductVariantUseCase, GetProductVariantQuery, DeleteProductVariantUseCase {

    private final ProductVariantRepository variantRepository;
    private final ProductRepository productRepository;

    public ProductVariantService(ProductVariantRepository variantRepository, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductVariant createVariant(CreateProductVariantCommand command) {
        productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException(command.productId()));

        variantRepository.findBySku(command.sku()).ifPresent(v -> {
            throw new ProductVariantSkuAlreadyExistsException(command.sku());
        });

        ProductVariant variant = ProductVariant.create(
                command.productId(),
                command.sku(),
                command.price(),
                command.quantity(),
                command.imageUrl()
        );
        return variantRepository.save(variant);
    }

    @Override
    @Transactional
    public ProductVariant update(UpdateProductVariantCommand command) {
        ProductVariant variant = variantRepository.findById(command.variantId())
                .orElseThrow(() -> new ProductVariantNotFoundException(command.variantId()));

        if (command.price() != null) variant.changePrice(command.price());
        if (command.quantity() != null) variant.setStock(command.quantity());
        if (command.imageUrl() != null) variant.changeImage(command.imageUrl());
        if (command.status() != null) variant.changeStatus(command.status());

        return variantRepository.save(variant);
    }

    @Override
    public Optional<ProductVariant> findById(Long variantId) {
        return variantRepository.findById(variantId);
    }

    @Override
    public Page<ProductVariant> findAllByProductId(Long productId, Pageable pageable) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return variantRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public void deleteById(Long variantId) {
        variantRepository.deleteById(variantId);
    }
}
