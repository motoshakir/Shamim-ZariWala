package com.shamimzariwala.categories.application.category.port.input;

import com.shamimzariwala.categories.application.category.command.UpdateCategoryCommand;
import com.shamimzariwala.categories.domain.category.Category;

public interface UpdateCategoryUseCase {
    Category update(UpdateCategoryCommand command);
}
