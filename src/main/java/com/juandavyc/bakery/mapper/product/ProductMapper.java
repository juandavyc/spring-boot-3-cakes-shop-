package com.juandavyc.bakery.mapper.product;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.product.request.ProductCreateRequestDTO;
import com.juandavyc.bakery.dto.product.response.*;
import com.juandavyc.bakery.dto.productimage.request.ProductImageCreateRequestDTO;
import com.juandavyc.bakery.entity.*;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug",source = "name", qualifiedByName = "toSlug")
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "productOccasions", ignore = true)
    @Mapping(target = "productImages", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductEntity productCreateRequestDTOToProductEntity(ProductCreateRequestDTO productCreateRequestDTO);

    ProductCreatedResponseDTO productEntityToProductCreatedResponseDTO(ProductEntity productEntity);

    //@Mapping(target = "id", source = "productCategoryId")
    ProductResponseDTO productEntityToProductResponseDTO(ProductEntity productEntity);

    ProductDetailedResponseDTO productEntityToProductDetailedResponseDTO(ProductEntity productEntity);

    ProductCategoriesUpdatedResponseDTO productEntityToProductCategoriesUpdatedResponseDTO(ProductEntity productEntity);

    ProductOccasionsUpdatedResponseDTO productEntityToProductOccasionsUpdatedResponseDTO(ProductEntity productEntity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productEntity", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductImageEntity productImageRequestDTOToProductImageEntity(ProductImageCreateRequestDTO productImageRequestDTO);

    ProductImagesUpdatedResponseDTO productEntityToProductImagesUpdatedResponseDTO(ProductEntity productEntity);

    @Named("toSlug")
    default String toSlug(String slug) {
        return SlugUtil.toSlug(slug);
    }

    @Named(value = "mapOccasions")
    default Set<ProductOccasionEntity> mapOccasions(Set<Integer> ids, ProductEntity productEntity, Integer userId) {

        if (ids.isEmpty() || productEntity == null || userId == null) {
            return Collections.emptySet();
        }

        return ids.stream()
                .map(id -> ProductOccasionEntity.builder()
                        .productOccasionId(
                                ProductOccasionId.builder()
                                        .productId(productEntity.getId())
                                        .occasionId(id)
                                        .build())
                        .occasion(OccasionEntity.builder().id(id).build())
                        .product(productEntity)
                        .addedBy(UserEntity.builder().id(userId).build())
                        .build())
                .collect(Collectors.toSet());

    }


    @Named(value = "mapCategories")
    default Set<ProductCategoryEntity> mapCategories(Set<Integer> ids, ProductEntity productEntity, Integer userId) {

        if (ids.isEmpty() || productEntity == null || userId == null) {
            return Collections.emptySet();
        }

        return ids.stream()
                .map(id -> ProductCategoryEntity.builder()
                        .productCategoryId(
                                ProductCategoryId.builder()
                                        .productId(productEntity.getId())
                                        .categoryId(id)
                                        .build())
                        .category(CategoryEntity.builder().id(id).build())
                        .product(productEntity)
                        .addedBy(UserEntity.builder().id(userId).build())
                        .build())
                .collect(Collectors.toSet());

    }



}
