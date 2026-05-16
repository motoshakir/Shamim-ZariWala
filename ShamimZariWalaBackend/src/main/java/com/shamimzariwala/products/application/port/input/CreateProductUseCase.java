package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.CreateProductCommand;
import com.shamimzariwala.products.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(CreateProductCommand command);
}
