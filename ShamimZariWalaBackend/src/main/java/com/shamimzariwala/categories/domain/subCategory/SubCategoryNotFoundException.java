package com.shamimzariwala.categories.domain.subCategory;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(Long id) {
        super("Sub-category with ID " + id + " was not found.");
    }
}
