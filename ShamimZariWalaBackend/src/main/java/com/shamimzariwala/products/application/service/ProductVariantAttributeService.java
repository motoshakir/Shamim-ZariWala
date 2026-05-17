package com.shamimzariwala.products.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.products.application.command.CreateProductVariantAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantAttributeCommand;
import com.shamimzariwala.products.application.port.input.CreateProductVariantAttributeUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductVariantAttributeUseCase;
import com.shamimzariwala.products.application.port.input.GetProductVariantAttributeQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductVariantAttributeUseCase;
import com.shamimzariwala.products.application.port.output.ProductVariantAttributeRepository;
import com.shamimzariwala.products.application.port.output.ProductVariantRepository;
import com.shamimzariwala.products.domain.exception.ProductVariantAttributeNotFoundException;
import com.shamimzariwala.products.domain.exception.ProductVariantNotFoundException;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public class ProductVariantAttributeService implements
        CreateProductVariantAttributeUseCase, UpdateProductVariantAttributeUseCase, GetProductVariantAttributeQuery, DeleteProductVariantAttributeUseCase {

    private final ProductVariantAttributeRepository attributeRepository;
    private final ProductVariantRepository variantRepository;

    public ProductVariantAttributeService(ProductVariantAttributeRepository attributeRepository, ProductVariantRepository variantRepository) {
        this.attributeRepository = attributeRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public ProductVariantAttribute createAttribute(CreateProductVariantAttributeCommand command) {
        variantRepository.findById(command.variantId())
                .orElseThrow(() -> new ProductVariantNotFoundException(command.variantId()));

        ProductVariantAttribute attribute = ProductVariantAttribute.create(command.variantId(), command.name(), command.value());
        return attributeRepository.save(attribute);
    }

    @Override
    @Transactional
    public ProductVariantAttribute update(UpdateProductVariantAttributeCommand command) {
        ProductVariantAttribute attribute = attributeRepository.findById(command.attributeId())
                .orElseThrow(() -> new ProductVariantAttributeNotFoundException(command.attributeId()));

        attribute.update(command.name(), command.value());
        return attributeRepository.save(attribute);
    }

    @Override
    public Optional<ProductVariantAttribute> findById(Long attributeId) {
        return attributeRepository.findById(attributeId);
    }

    @Override
    public Page<ProductVariantAttribute> findAllByVariantId(Long variantId, Pageable pageable) {
        variantRepository.findById(variantId)
                .orElseThrow(() -> new ProductVariantNotFoundException(variantId));
        return attributeRepository.findAllByVariantId(variantId, pageable);
    }

    @Override
    public void deleteById(Long attributeId) {
        attributeRepository.deleteById(attributeId);
    }
}
