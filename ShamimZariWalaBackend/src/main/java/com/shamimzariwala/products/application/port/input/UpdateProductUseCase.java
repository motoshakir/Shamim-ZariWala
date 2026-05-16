package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.UpdateProductCommand;
import com.shamimzariwala.products.domain.model.Product;

public interface UpdateProductUseCase {
    Product update(UpdateProductCommand command);
}
