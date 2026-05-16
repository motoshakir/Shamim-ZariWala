package com.shamimzariwala.products.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.products.application.command.CreateProductAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductAttributeCommand;
import com.shamimzariwala.products.application.port.input.CreateProductAttributeUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductAttributeUseCase;
import com.shamimzariwala.products.application.port.input.GetProductAttributeQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductAttributeUseCase;
import com.shamimzariwala.products.application.port.output.ProductAttributeRepository;
import com.shamimzariwala.products.application.port.output.ProductRepository;
import com.shamimzariwala.products.domain.exception.ProductAttributeNotFoundException;
import com.shamimzariwala.products.domain.exception.ProductNotFoundException;
import com.shamimzariwala.products.domain.model.ProductAttribute;

public class ProductAttributeService implements
        CreateProductAttributeUseCase, UpdateProductAttributeUseCase, GetProductAttributeQuery, DeleteProductAttributeUseCase {

    private final ProductAttributeRepository attributeRepository;
    private final ProductRepository productRepository;

    public ProductAttributeService(ProductAttributeRepository attributeRepository, ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductAttribute createAttribute(CreateProductAttributeCommand command) {
        productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException(command.productId()));

        ProductAttribute attribute = ProductAttribute.create(command.productId(), command.name(), command.value());
        return attributeRepository.save(attribute);
    }

    @Override
    @Transactional
    public ProductAttribute update(UpdateProductAttributeCommand command) {
        ProductAttribute attribute = attributeRepository.findById(command.attributeId())
                .orElseThrow(() -> new ProductAttributeNotFoundException(command.attributeId()));

        attribute.update(command.name(), command.value());
        return attributeRepository.save(attribute);
    }

    @Override
    public Optional<ProductAttribute> findById(Long attributeId) {
        return attributeRepository.findById(attributeId);
    }

    @Override
    public Page<ProductAttribute> findAllByProductId(Long productId, Pageable pageable) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return attributeRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public void deleteById(Long attributeId) {
        attributeRepository.deleteById(attributeId);
    }
}
