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
import com.shamimzariwala.products.application.command.CreateProductVariantCommand;
import com.shamimzariwala.products.application.command.UpdateProductVariantCommand;
import com.shamimzariwala.products.application.port.input.CreateProductVariantUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductVariantUseCase;
import com.shamimzariwala.products.application.port.input.GetProductVariantQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductVariantUseCase;
import com.shamimzariwala.products.domain.exception.ProductVariantNotFoundException;
import com.shamimzariwala.products.domain.model.ProductVariant;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}/variants")
@Tag(name = "Product Variants", description = "Buyable variants (SKU, price, stock) for a product")
public class ProductVariantController {

    private final CreateProductVariantUseCase createVariantUseCase;
    private final UpdateProductVariantUseCase updateVariantUseCase;
    private final GetProductVariantQuery getVariantQuery;
    private final DeleteProductVariantUseCase deleteVariantUseCase;

    public ProductVariantController(CreateProductVariantUseCase createVariantUseCase,
                                    UpdateProductVariantUseCase updateVariantUseCase,
                                    GetProductVariantQuery getVariantQuery,
                                    DeleteProductVariantUseCase deleteVariantUseCase) {
        this.createVariantUseCase = createVariantUseCase;
        this.updateVariantUseCase = updateVariantUseCase;
        this.getVariantQuery = getVariantQuery;
        this.deleteVariantUseCase = deleteVariantUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductVariantResponse> create(@PathVariable Long productId,
                                                         @Valid @RequestBody CreateProductVariantRequest request) {
        CreateProductVariantCommand command = ProductVariantMapper.toCommand(productId, request);
        ProductVariant variant = createVariantUseCase.createVariant(command);
        ProductVariantResponse response = ProductVariantMapper.toResponse(variant);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/{pid}/variants/{vid}")
                .buildAndExpand(productId, variant.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{variantId}")
    public ProductVariantResponse update(@PathVariable Long productId,
                                         @PathVariable Long variantId,
                                         @Valid @RequestBody UpdateProductVariantRequest request) {
        UpdateProductVariantCommand command = ProductVariantMapper.toCommand(variantId, request);
        ProductVariant variant = updateVariantUseCase.update(command);
        return ProductVariantMapper.toResponse(variant);
    }

    @GetMapping("/{variantId}")
    public ProductVariantResponse getById(@PathVariable Long productId, @PathVariable Long variantId) {
        ProductVariant variant = getVariantQuery.findById(variantId)
                .orElseThrow(() -> new ProductVariantNotFoundException(variantId));
        return ProductVariantMapper.toResponse(variant);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<ProductVariantResponse> list(@PathVariable Long productId,
                                                     @ParameterObject Pageable pageable) {
        Page<ProductVariant> variants = getVariantQuery.findAllByProductId(productId, pageable);
        Page<ProductVariantResponse> response = variants.map(ProductVariantMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{variantId}")
    public void delete(@PathVariable Long productId, @PathVariable Long variantId) {
        deleteVariantUseCase.deleteById(variantId);
    }
}
