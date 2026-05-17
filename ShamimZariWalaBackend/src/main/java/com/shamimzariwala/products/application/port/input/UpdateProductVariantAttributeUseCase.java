package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.UpdateProductVariantAttributeCommand;
import com.shamimzariwala.products.domain.model.ProductVariantAttribute;

public interface UpdateProductVariantAttributeUseCase {
    ProductVariantAttribute update(UpdateProductVariantAttributeCommand command);
}
