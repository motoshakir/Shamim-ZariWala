package com.shamimzariwala.products.application.port.input;

import com.shamimzariwala.products.application.command.UpdateProductVariantCommand;
import com.shamimzariwala.products.domain.model.ProductVariant;

public interface UpdateProductVariantUseCase {
    ProductVariant update(UpdateProductVariantCommand command);
}
