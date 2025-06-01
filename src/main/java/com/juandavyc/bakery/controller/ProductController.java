package com.juandavyc.bakery.controller;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.product.request.*;
import com.juandavyc.bakery.dto.product.response.*;
import com.juandavyc.bakery.dto.productoccasion.ProductOccasionResponseDTO;

import com.juandavyc.bakery.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.util.Set;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/available")
    public ResponseEntity<Boolean> availableByName(
            @RequestParam String name
    ){
        return ResponseEntity.ok(productService.availableByName(name));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponseDTO>> getProductsPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(productService.getProductsPage(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDetailedResponseDTO> getProductById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(productService.getProductByID(id));
    }

    @GetMapping(path = "/slug/{slug}")
    public ResponseEntity<ProductDetailedResponseDTO> getProductBySlug(
            @PathVariable String slug
    ) {
        return ResponseEntity.ok(productService.getProductBySlug(slug));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PageResponse<ProductResponseDTO>> getProductsSearch(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "minPrice", required = false) BigInteger minPrice,
            @RequestParam(name = "maxPrice", required = false) BigInteger maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String occasion,
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                productService.getProductsSearch(
                        name,
                        minPrice,
                        maxPrice,
                        category,
                        occasion,
                        pageable
                )
        );
    }

    @PostMapping()
    public ResponseEntity<ProductCreatedResponseDTO> create(
            @Valid @RequestBody ProductCreateRequestDTO productCreateRequestDTO
    ) {
        final var productCreated = productService.create(productCreateRequestDTO);
        return ResponseEntity.created(
                URI.create("/api/products/"+productCreated.id().toString())
        ).body(productCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO
    ) {
        return ResponseEntity.ok(productService.update(id,productUpdateRequestDTO));
    }

    @PutMapping(path = "/{id}/categories")
    public ResponseEntity<ProductCategoriesUpdatedResponseDTO> updateCategories(
            @PathVariable UUID id,
            @Valid @RequestBody ProductCategoriesUpdateRequestDTO productCategoriesUpdateRequestDTO
            ) {
        return ResponseEntity.ok(productService.updateCategories(id,productCategoriesUpdateRequestDTO));
    }

    @PutMapping(path = "/{id}/occasions")
    public ResponseEntity<ProductOccasionsUpdatedResponseDTO> updateOccasions(
            @PathVariable UUID id,
            @Valid @RequestBody ProductOccasionsUpdateRequestDTO productOccasionsUpdateRequestDTO
    ) {
        return ResponseEntity.ok(productService.updateOccasions(id,productOccasionsUpdateRequestDTO));
    }

    @PutMapping(path = "/{id}/images")
    public ResponseEntity<ProductImagesUpdatedResponseDTO> updateImages(
            @PathVariable UUID id,
            @Valid @RequestBody ProductImagesUpdateRequestDTO productImagesUpdateRequestDTO
    ) {
        return ResponseEntity.ok(productService.updateImages(id,productImagesUpdateRequestDTO));
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
