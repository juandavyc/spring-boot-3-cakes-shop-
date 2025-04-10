package com.juandavyc.bakery.controller;

import com.juandavyc.bakery.dto.category.request.CategoryCreateRequestDTO;
import com.juandavyc.bakery.dto.category.response.CategoryCreatedResponseDTO;
import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;

import com.juandavyc.bakery.service.category.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<PageResponse<CategoryResponseDTO>> getCategoriesPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(categoryService.getCategoriesPage(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoriesById(
           @PathVariable Integer id
    ) {
        return ResponseEntity.ok(categoryService.getCategoriesById(id));
    }

    @GetMapping(path = "/slug/{slug}")
    public ResponseEntity<CategoryResponseDTO> getCategoriesBySlug(
            @PathVariable String slug
    ) {
        return ResponseEntity.ok(categoryService.getCategoriesBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<CategoryCreatedResponseDTO> create(
            @RequestBody CategoryCreateRequestDTO categoryCreateRequestDTO
    ) {
        final var categoryCreated = categoryService.create(categoryCreateRequestDTO);
        return ResponseEntity
                .created(URI.create("/api/categories/" + categoryCreated.id()))
                .body(categoryCreated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
