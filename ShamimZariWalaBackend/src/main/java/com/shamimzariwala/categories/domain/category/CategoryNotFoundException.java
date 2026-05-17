package com.shamimzariwala.categories.domain.category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category with ID " + id + " was not found.");
    }
}
