package com.shamimzariwala.categories.application.subCategory.port.input;

import com.shamimzariwala.categories.application.subCategory.command.UpdateSubCategoryCommand;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;

public interface UpdateSubCategoryUseCase {
    SubCategory update(UpdateSubCategoryCommand command);
}
