package com.juandavyc.bakery.service.product;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.product.request.ProductCreateRequestDTO;
import com.juandavyc.bakery.dto.product.response.ProductCreatedResponseDTO;
import com.juandavyc.bakery.entity.*;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;
import com.juandavyc.bakery.repository.CategoryRepository;
import com.juandavyc.bakery.repository.OccasionRepository;
import com.juandavyc.bakery.repository.ProductRepository;
import com.juandavyc.bakery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final OccasionRepository occasionRepository;

    private final UserRepository userRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public ProductCreatedResponseDTO create(ProductCreateRequestDTO productCreateRequestDTO) {

        final var userId = productCreateRequestDTO.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        final var productEntity = ProductEntity.builder()
                .name(productCreateRequestDTO.getName())
                .slug(SlugUtil.toSlug(productCreateRequestDTO.getName()))
                .description(productCreateRequestDTO.getDescription())
                .price(productCreateRequestDTO.getPrice())
                .cover(productCreateRequestDTO.getCover())
                .build();


        final var productCreateCategoryIds = productCreateRequestDTO.getCategoryIds();
        if (!productCreateCategoryIds.isEmpty()) {

            final var count = categoryRepository.countByIdIn(productCreateCategoryIds);
            if (count != productCreateCategoryIds.size()) {
                throw new IllegalArgumentException("Category Ids do not match");
            }
            Set<ProductCategoryEntity> categoryEntities = productCreateCategoryIds.stream()
                    .peek(System.out::println)
                    .map(id ->
                            ProductCategoryEntity.builder()
                                    .productCategoryId(ProductCategoryId.builder()
                                            .productId(productEntity.getId())
                                            .categoryId(id)
                                            .build())
                                    .product(productEntity)
                                    .category(CategoryEntity.builder().id(id).build())
                                    .addedBy(UserEntity.builder().id(userId).build())
                                    .build()
                    )
                    .collect(Collectors.toSet());

            productEntity.setProductCategories(categoryEntities);
        }


        final var productCreateOccasionIds = productCreateRequestDTO.getOccasionIds();
        if (!productCreateOccasionIds.isEmpty()) {

            final var count = occasionRepository.countByIdIn(productCreateOccasionIds);
            if (count != productCreateOccasionIds.size()) {
                throw new IllegalArgumentException("Occasion Ids do not match");
            }
            Set<ProductOccasionEntity> productOccasionEntities = productCreateOccasionIds.stream()
                    .map(id -> ProductOccasionEntity.builder()
                            .productOccasionId(
                                    ProductOccasionId.builder()
                                            .productId(productEntity.getId())
                                            .occasionId(id)
                                            .build())
                            .product(productEntity)
                            .addedBy(UserEntity.builder().id(userId).build())
                            .build())
                    .collect(Collectors.toSet());

            productEntity.setProductOccasions(productOccasionEntities);
        }


        final var productCreated = productRepository.save(productEntity);

        return new ProductCreatedResponseDTO(
                productCreated.getId(),
                productCreated.getName(),
                productCreated.getSlug(),
                productCreated.getPrice()
        );
    }
}
