package com.shamimzariwala.products.application.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.products.domain.model.Product;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long productId);
    Optional<Product> findByName(String name);
    void deleteById(Long productId);
    Page<Product> findAll(Pageable pageable);
}
