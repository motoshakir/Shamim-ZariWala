package com.shamimzariwala.products.application.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.Product;

public interface GetProductQuery {
    Optional<Product> findById(Long productId);
    Page<Product> findAll(Pageable pageable);
}
