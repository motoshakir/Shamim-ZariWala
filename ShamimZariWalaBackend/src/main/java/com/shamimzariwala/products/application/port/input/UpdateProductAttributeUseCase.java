package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.UpdateProductAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductAttribute;

public interface UpdateProductAttributeUseCase {
    ProductAttribute update(UpdateProductAttributeCommand command);
}
