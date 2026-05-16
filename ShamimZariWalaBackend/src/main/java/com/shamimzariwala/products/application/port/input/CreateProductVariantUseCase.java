package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.CreateProductVariantCommand;
import com.shamimzariwala.products.domain.model.ProductVariant;

public interface CreateProductVariantUseCase {
    ProductVariant createVariant(CreateProductVariantCommand command);
}
