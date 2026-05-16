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
import com.shamimzariwala.products.application.command.CreateProductCommand;
import com.shamimzariwala.products.application.command.UpdateProductCommand;
import com.shamimzariwala.products.application.port.input.CreateProductUseCase;
import com.shamimzariwala.products.application.port.input.DeleteProductUseCase;
import com.shamimzariwala.products.application.port.input.GetProductQuery;
import com.shamimzariwala.products.application.port.input.UpdateProductUseCase;
import com.shamimzariwala.products.domain.exception.ProductNotFoundException;
import com.shamimzariwala.products.domain.model.Product;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management", description = "Operations related to the product catalog")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductQuery getProductQuery;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             GetProductQuery getProductQuery,
                             DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.getProductQuery = getProductQuery;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {

        CreateProductCommand command = ProductMapper.toCommand(request);
        Product product = createProductUseCase.createProduct(command);
        ProductResponse response = ProductMapper.toResponse(product);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/products/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/update/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {

        UpdateProductCommand command = ProductMapper.toCommand(id, request);
        Product product = updateProductUseCase.update(command);

        return ProductMapper.toResponse(product);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        Product product = getProductQuery.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toResponse(product);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<ProductResponse> getProducts(@ParameterObject Pageable pageable) {

        Page<Product> products = getProductQuery.findAll(pageable);
        Page<ProductResponse> response = products.map(ProductMapper::toResponse);

        return new PageResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        deleteProductUseCase.deleteById(id);
    }
}
