package com.juandavyc.bakery.controller.open;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.product.response.ProductDetailedResponseDTO;
import com.juandavyc.bakery.dto.product.response.ProductResponseDTO;
import com.juandavyc.bakery.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/public/products")
@RequiredArgsConstructor
public class ProductPublicController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponseDTO>> getProductsPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(productService.getProductsPage(pageable));
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

}
