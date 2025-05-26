package com.juandavyc.bakery.service.product;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.product.request.*;
import com.juandavyc.bakery.dto.product.response.*;
import com.juandavyc.bakery.dto.productoccasion.ProductOccasionResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

public interface ProductService {

    ProductCreatedResponseDTO create(ProductCreateRequestDTO productCreateRequestDTO);

    PageResponse<ProductResponseDTO> getProductsPage(Pageable pageable);

    ProductDetailedResponseDTO getProductByID(UUID id);

    ProductDetailedResponseDTO getProductBySlug(String slug);

    PageResponse<ProductResponseDTO> getProductsSearch(
            String name,
            BigInteger minPrice,
            BigInteger maxPrice,
            String category,
            String occasion,
            Pageable pageable
    );

    ProductCategoriesUpdatedResponseDTO updateCategories(
            UUID id,
            ProductCategoriesUpdateRequestDTO productCategoriesUpdateRequestDTO
    );

    ProductOccasionsUpdatedResponseDTO updateOccasions(
            UUID id,
            ProductOccasionsUpdateRequestDTO productOccasionsUpdateRequestDTO
    );

    ProductResponseDTO update(UUID id, ProductUpdateRequestDTO productUpdateRequestDTO);

    void delete(UUID id);

    ProductImagesUpdatedResponseDTO updateImages(UUID id, ProductImagesUpdateRequestDTO productImagesUpdateRequestDTO);

    Boolean availableByName(String name);
}
