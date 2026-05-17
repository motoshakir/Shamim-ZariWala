package com.shamimzariwala.categories.adapter.input.rest.category;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shamimzariwala.categories.application.category.command.CreateCategoryCommand;
import com.shamimzariwala.categories.application.category.command.UpdateCategoryCommand;
import com.shamimzariwala.categories.application.category.port.input.CreateCategoryUseCase;
import com.shamimzariwala.categories.application.category.port.input.DeleteCategoryUseCase;
import com.shamimzariwala.categories.application.category.port.input.GetCategoryQuery;
import com.shamimzariwala.categories.application.category.port.input.UpdateCategoryUseCase;
import com.shamimzariwala.categories.domain.category.Category;
import com.shamimzariwala.categories.domain.category.CategoryNotFoundException;
import com.shamimzariwala.common.response.PageResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Top-level product categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final GetCategoryQuery getCategoryQuery;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              GetCategoryQuery getCategoryQuery,
                              DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.getCategoryQuery = getCategoryQuery;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryRequest request) {
        CreateCategoryCommand command = CategoryMapper.toCommand(request);
        Category category = createCategoryUseCase.createCategory(command);
        CategoryResponse response = CategoryMapper.toResponse(category);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/categories/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse update(@PathVariable Long categoryId, @Valid @RequestBody UpdateCategoryRequest request) {
        UpdateCategoryCommand command = CategoryMapper.toCommand(categoryId, request);
        Category category = updateCategoryUseCase.update(command);
        return CategoryMapper.toResponse(category);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getById(@PathVariable Long categoryId) {
        Category category = getCategoryQuery.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return CategoryMapper.toResponse(category);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<CategoryResponse> list(@ParameterObject Pageable pageable) {
        Page<Category> categories = getCategoryQuery.findAll(pageable);
        Page<CategoryResponse> response = categories.map(CategoryMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        deleteCategoryUseCase.deleteById(categoryId);
    }
}
