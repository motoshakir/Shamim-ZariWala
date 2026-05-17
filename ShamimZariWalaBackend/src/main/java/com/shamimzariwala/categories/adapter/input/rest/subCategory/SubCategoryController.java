package com.shamimzariwala.categories.adapter.input.rest.subCategory;

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

import com.shamimzariwala.categories.application.subCategory.command.CreateSubCategoryCommand;
import com.shamimzariwala.categories.application.subCategory.command.UpdateSubCategoryCommand;
import com.shamimzariwala.categories.application.subCategory.port.input.CreateSubCategoryUseCase;
import com.shamimzariwala.categories.application.subCategory.port.input.DeleteSubCategoryUseCase;
import com.shamimzariwala.categories.application.subCategory.port.input.GetSubCategoryQuery;
import com.shamimzariwala.categories.application.subCategory.port.input.UpdateSubCategoryUseCase;
import com.shamimzariwala.categories.domain.subCategory.SubCategory;
import com.shamimzariwala.categories.domain.subCategory.SubCategoryNotFoundException;
import com.shamimzariwala.common.response.PageResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories/{categoryId}/sub-categories")
@Tag(name = "Sub-Categories", description = "Sub-categories owned by a parent category")
public class SubCategoryController {

    private final CreateSubCategoryUseCase createSubCategoryUseCase;
    private final UpdateSubCategoryUseCase updateSubCategoryUseCase;
    private final GetSubCategoryQuery getSubCategoryQuery;
    private final DeleteSubCategoryUseCase deleteSubCategoryUseCase;

    public SubCategoryController(CreateSubCategoryUseCase createSubCategoryUseCase,
                                 UpdateSubCategoryUseCase updateSubCategoryUseCase,
                                 GetSubCategoryQuery getSubCategoryQuery,
                                 DeleteSubCategoryUseCase deleteSubCategoryUseCase) {
        this.createSubCategoryUseCase = createSubCategoryUseCase;
        this.updateSubCategoryUseCase = updateSubCategoryUseCase;
        this.getSubCategoryQuery = getSubCategoryQuery;
        this.deleteSubCategoryUseCase = deleteSubCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<SubCategoryResponse> create(@PathVariable Long categoryId,
                                                      @Valid @RequestBody CreateSubCategoryRequest request) {
        CreateSubCategoryCommand command = SubCategoryMapper.toCommand(categoryId, request);
        SubCategory subCategory = createSubCategoryUseCase.createSubCategory(command);
        SubCategoryResponse response = SubCategoryMapper.toResponse(subCategory);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/categories/{cid}/sub-categories/{sid}")
                .buildAndExpand(categoryId, subCategory.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{subCategoryId}")
    public SubCategoryResponse update(@PathVariable Long categoryId,
                                      @PathVariable Long subCategoryId,
                                      @Valid @RequestBody UpdateSubCategoryRequest request) {
        UpdateSubCategoryCommand command = SubCategoryMapper.toCommand(subCategoryId, request);
        SubCategory subCategory = updateSubCategoryUseCase.update(command);
        return SubCategoryMapper.toResponse(subCategory);
    }

    @GetMapping("/{subCategoryId}")
    public SubCategoryResponse getById(@PathVariable Long categoryId, @PathVariable Long subCategoryId) {
        SubCategory subCategory = getSubCategoryQuery.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException(subCategoryId));
        return SubCategoryMapper.toResponse(subCategory);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<SubCategoryResponse> list(@PathVariable Long categoryId,
                                                  @ParameterObject Pageable pageable) {
        Page<SubCategory> subCategories = getSubCategoryQuery.findAllByCategoryId(categoryId, pageable);
        Page<SubCategoryResponse> response = subCategories.map(SubCategoryMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{subCategoryId}")
    public void delete(@PathVariable Long categoryId, @PathVariable Long subCategoryId) {
        deleteSubCategoryUseCase.deleteById(subCategoryId);
    }
}
