package com.shamimzariwala.products.adapter.input.rest;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shamimzariwala.common.response.PageResponse;
import com.shamimzariwala.products.application.command.CreateProductAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductAttributeCommand;
import com.shamimzariwala.products.application.port.input.CreateProductAttributeUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductAttributeUseCase;
import com.shamimzariwala.products.application.port.input.GetProductAttributeQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductAttributeUseCase;
import com.shamimzariwala.products.domain.exception.ProductAttributeNotFoundException;
import com.shamimzariwala.products.domain.model.ProductAttribute;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/attributes")
@Tag(name = "Product Attributes", description = "Descriptive key/value pairs for a product (material, pattern, etc.)")
public class ProductAttributeController {

    private final CreateProductAttributeUseCase createAttributeUseCase;
    private final UpdateProductAttributeUseCase updateAttributeUseCase;
    private final GetProductAttributeQuery getAttributeQuery;
    private final DeleteProductAttributeUseCase deleteAttributeUseCase;

    public ProductAttributeController(CreateProductAttributeUseCase createAttributeUseCase,
                                      UpdateProductAttributeUseCase updateAttributeUseCase,
                                      GetProductAttributeQuery getAttributeQuery,
                                      DeleteProductAttributeUseCase deleteAttributeUseCase) {
        this.createAttributeUseCase = createAttributeUseCase;
        this.updateAttributeUseCase = updateAttributeUseCase;
        this.getAttributeQuery = getAttributeQuery;
        this.deleteAttributeUseCase = deleteAttributeUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductAttributeResponse> create(@PathVariable Long productId,
                                                           @Valid @RequestBody CreateProductAttributeRequest request) {
        CreateProductAttributeCommand command = ProductAttributeMapper.toCommand(productId, request);
        ProductAttribute attribute = createAttributeUseCase.createAttribute(command);
        ProductAttributeResponse response = ProductAttributeMapper.toResponse(attribute);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/{pid}/attributes/{aid}")
                .buildAndExpand(productId, attribute.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{attributeId}")
    public ProductAttributeResponse update(@PathVariable Long productId,
                                           @PathVariable Long attributeId,
                                           @Valid @RequestBody UpdateProductAttributeRequest request) {
        UpdateProductAttributeCommand command = ProductAttributeMapper.toCommand(attributeId, request);
        ProductAttribute attribute = updateAttributeUseCase.update(command);
        return ProductAttributeMapper.toResponse(attribute);
    }

    @GetMapping("/{attributeId}")
    public ProductAttributeResponse getById(@PathVariable Long productId, @PathVariable Long attributeId) {
        ProductAttribute attribute = getAttributeQuery.findById(attributeId)
                .orElseThrow(() -> new ProductAttributeNotFoundException(attributeId));
        return ProductAttributeMapper.toResponse(attribute);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<ProductAttributeResponse> list(@PathVariable Long productId,
                                                       @ParameterObject Pageable pageable) {
        Page<ProductAttribute> attributes = getAttributeQuery.findAllByProductId(productId, pageable);
        Page<ProductAttributeResponse> response = attributes.map(ProductAttributeMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{attributeId}")
    public void delete(@PathVariable Long productId, @PathVariable Long attributeId) {
        deleteAttributeUseCase.deleteById(attributeId);
    }
}
