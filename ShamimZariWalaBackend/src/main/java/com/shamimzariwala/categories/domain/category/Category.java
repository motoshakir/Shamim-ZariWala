package com.shamimzariwala.categories.domain.category;

import java.time.LocalDateTime;
import java.util.Objects;

public class Category {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    protected Category() {}

    private Category(String name, String description) {
        this.name = requireNonBlank(name, "Name is required");
        this.description = description;
    }

    public static Category create(String name, String description) {
        return new Category(name, description);
    }

    public static Category restore(Long id, String name, String description, LocalDateTime createdAt) {
        Category category = new Category();
        category.id = id;
        category.name = name;
        category.description = description;
        category.createdAt = createdAt;
        return category;
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
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
