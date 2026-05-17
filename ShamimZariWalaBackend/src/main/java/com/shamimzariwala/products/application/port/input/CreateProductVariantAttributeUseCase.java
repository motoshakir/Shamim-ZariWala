package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.CreateProductVariantAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public interface CreateProductVariantAttributeUseCase {
    ProductVariantAttribute createAttribute(CreateProductVariantAttributeCommand command);
}
