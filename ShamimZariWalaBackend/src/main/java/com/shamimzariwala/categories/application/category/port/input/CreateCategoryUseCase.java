package com.shamimzariwala.categories.application.category.port.input;

import com.shamimzariwala.categories.application.category.command.CreateCategoryCommand;
import com.shamimzariwala.categories.domain.category.Category;

public interface CreateCategoryUseCase {
    Category createCategory(CreateCategoryCommand command);
}
