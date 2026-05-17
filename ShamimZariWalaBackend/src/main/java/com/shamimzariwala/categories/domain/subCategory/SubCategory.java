package com.shamimzariwala.categories.domain.subCategory;

import java.time.LocalDateTime;
import java.util.Objects;

public class SubCategory {

    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    protected SubCategory() {}

    private SubCategory(Long categoryId, String name, String description) {
        this.categoryId = Objects.requireNonNull(categoryId, "Category id is required");
        this.name = requireNonBlank(name, "Name is required");
        this.description = description;
    }

    public static SubCategory create(Long categoryId, String name, String description) {
        return new SubCategory(categoryId, name, description);
    }

    public static SubCategory restore(Long id, Long categoryId, String name, String description, LocalDateTime createdAt) {
        SubCategory subCategory = new SubCategory();
        subCategory.id = id;
        subCategory.categoryId = categoryId;
        subCategory.name = name;
        subCategory.description = description;
        subCategory.createdAt = createdAt;
        return subCategory;
    }

    public void update(String name, String description) {
        if (name != null && !name.isBlank()) this.name = name;
        if (description != null) this.description = description;
    }

    private static String requireNonBlank(String s, String message) {
        if (s == null || s.isBlank()) throw new IllegalArgumentException(message);
        return s;
    }

    public Long getId() { return id; }
    public Long getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
