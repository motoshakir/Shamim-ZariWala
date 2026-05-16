package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.CreateProductAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductAttribute;

public interface CreateProductAttributeUseCase {
    ProductAttribute createAttribute(CreateProductAttributeCommand command);
}
