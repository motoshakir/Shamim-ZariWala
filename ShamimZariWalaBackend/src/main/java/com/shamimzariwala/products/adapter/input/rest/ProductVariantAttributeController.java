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
import com.shamimzariwala.products.application.command.CreateProductVariantAttributeCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantAttributeCommand;
import com.shamimzariwala.products.application.port.input.CreateProductVariantAttributeUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductVariantAttributeUseCase;
import com.shamimzariwala.products.application.port.input.GetProductVariantAttributeQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductVariantAttributeUseCase;
import com.shamimzariwala.products.domain.exception.ProductVariantAttributeNotFoundException;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/variants/{variantId}/attributes")
@Tag(name = "Product Variant Attributes", description = "Descriptive key/value pairs for a specific variant (size, color, etc.)")
public class ProductVariantAttributeController {

    private final CreateProductVariantAttributeUseCase createAttributeUseCase;
    private final UpdateProductVariantAttributeUseCase updateAttributeUseCase;
    private final GetProductVariantAttributeQuery getAttributeQuery;
    private final DeleteProductVariantAttributeUseCase deleteAttributeUseCase;

    public ProductVariantAttributeController(CreateProductVariantAttributeUseCase createAttributeUseCase,
                                             UpdateProductVariantAttributeUseCase updateAttributeUseCase,
                                             GetProductVariantAttributeQuery getAttributeQuery,
                                             DeleteProductVariantAttributeUseCase deleteAttributeUseCase) {
        this.createAttributeUseCase = createAttributeUseCase;
        this.updateAttributeUseCase = updateAttributeUseCase;
        this.getAttributeQuery = getAttributeQuery;
        this.deleteAttributeUseCase = deleteAttributeUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductVariantAttributeResponse> create(@PathVariable Long variantId,
                                                                  @Valid @RequestBody CreateProductVariantAttributeRequest request) {
        CreateProductVariantAttributeCommand command = ProductVariantAttributeMapper.toCommand(variantId, request);
        ProductVariantAttribute attribute = createAttributeUseCase.createAttribute(command);
        ProductVariantAttributeResponse response = ProductVariantAttributeMapper.toResponse(attribute);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/variants/{vid}/attributes/{aid}")
                .buildAndExpand(variantId, attribute.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{attributeId}")
    public ProductVariantAttributeResponse update(@PathVariable Long variantId,
                                                  @PathVariable Long attributeId,
                                                  @Valid @RequestBody UpdateProductVariantAttributeRequest request) {
        UpdateProductVariantAttributeCommand command = ProductVariantAttributeMapper.toCommand(attributeId, request);
        ProductVariantAttribute attribute = updateAttributeUseCase.update(command);
        return ProductVariantAttributeMapper.toResponse(attribute);
    }

    @GetMapping("/{attributeId}")
    public ProductVariantAttributeResponse getById(@PathVariable Long variantId, @PathVariable Long attributeId) {
        ProductVariantAttribute attribute = getAttributeQuery.findById(attributeId)
                .orElseThrow(() -> new ProductVariantAttributeNotFoundException(attributeId));
        return ProductVariantAttributeMapper.toResponse(attribute);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<ProductVariantAttributeResponse> list(@PathVariable Long variantId,
                                                              @ParameterObject Pageable pageable) {
        Page<ProductVariantAttribute> attributes = getAttributeQuery.findAllByVariantId(variantId, pageable);
        Page<ProductVariantAttributeResponse> response = attributes.map(ProductVariantAttributeMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{attributeId}")
    public void delete(@PathVariable Long variantId, @PathVariable Long attributeId) {
        deleteAttributeUseCase.deleteById(attributeId);
    }
}
