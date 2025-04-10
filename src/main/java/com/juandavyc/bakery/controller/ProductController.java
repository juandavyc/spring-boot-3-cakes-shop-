package com.juandavyc.bakery.controller;

import com.juandavyc.bakery.dto.product.request.ProductCreateRequestDTO;
import com.juandavyc.bakery.dto.product.response.ProductCreatedResponseDTO;
import com.juandavyc.bakery.entity.ProductEntity;
import com.juandavyc.bakery.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductCreatedResponseDTO> create(
            @Valid @RequestBody ProductCreateRequestDTO productCreateRequestDTO
    ) {
        return ResponseEntity.ok(productService.create(productCreateRequestDTO));
    }


}
