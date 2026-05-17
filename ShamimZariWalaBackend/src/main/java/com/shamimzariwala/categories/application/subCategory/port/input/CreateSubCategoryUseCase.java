package com.shamimzariwala.categories.application.subCategory.port.input;

import com.shamimzariwala.categories.application.subCategory.command.CreateSubCategoryCommand;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;

public interface CreateSubCategoryUseCase {
    SubCategory createSubCategory(CreateSubCategoryCommand command);
}
