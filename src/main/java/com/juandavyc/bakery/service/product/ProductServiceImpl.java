package com.juandavyc.bakery.service.product;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.product.request.*;
import com.juandavyc.bakery.dto.product.response.*;
import com.juandavyc.bakery.dto.productimage.request.ProductImageUpdateRequestDTO;
import com.juandavyc.bakery.entity.*;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.mapper.product.ProductMapper;
import com.juandavyc.bakery.mapper.product.ProductUpdateMapper;
import com.juandavyc.bakery.repository.*;
import com.juandavyc.bakery.specification.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final PageMapper pageMapper;

    private final ProductMapper productMapper;

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductOccasionRepository productOccasionRepository;

    private final CategoryRepository categoryRepository;
    private final OccasionRepository occasionRepository;

    private final ProductImageRepository productImageRepository;

    private final UserRepository userRepository;

    private final ProductUpdateMapper productUpdateMapper;

    @Override
    public PageResponse<ProductResponseDTO> getProductsPage(Pageable pageable) {

        final var productEntities = productRepository.findAll(pageable);

        return pageMapper.toPageResponse(productEntities, productMapper::productEntityToProductResponseDTO);
    }

    @Override
    public ProductDetailedResponseDTO getProductByID(UUID id) {

        final var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + ", not found"));


        return productMapper.productEntityToProductDetailedResponseDTO(productEntity);
        //return getProductDetailed(productEntity);
    }

    @Override
    public ProductDetailedResponseDTO getProductBySlug(String slug) {

        final var productEntity = productRepository.findBySlug(slug);
        if (productEntity == null) {
            throw new IllegalArgumentException("Product with slug: " + slug + " not found");
        }
        return productMapper.productEntityToProductDetailedResponseDTO(productEntity);

    }


    @Override
    public PageResponse<ProductResponseDTO> getProductsSearch(
            String name,
            BigInteger minPrice,
            BigInteger maxPrice,
            String category,
            String occasion,
            Pageable pageable) {


        Specification<ProductEntity> specification = Specification
                .where(ProductSpecifications.byName(name))
                .and(ProductSpecifications.byPriceGreaterThan(minPrice))
                .and(ProductSpecifications.byPriceLessThan(maxPrice))
                .and(ProductSpecifications.byCategorySlug(category))
                .and(ProductSpecifications.byOccasionSlug(occasion));

        final var productEntities = productRepository.findAll(specification, pageable);

        return pageMapper.toPageResponse(productEntities, productMapper::productEntityToProductResponseDTO);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ProductCreatedResponseDTO create(ProductCreateRequestDTO productCreateRequestDTO) {

        final var userId = productCreateRequestDTO.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        final var productEntity = productMapper
                .productCreateRequestDTOToProductEntity(productCreateRequestDTO);

        final var productCreated = productRepository.save(productEntity);

        final var categoryIds = productCreateRequestDTO.getCategoryIds();

        if (categoryIds != null && !categoryIds.isEmpty()) {
            final var count = categoryRepository.countByIdIn(categoryIds);
            if (count != categoryIds.size()) {
                throw new IllegalArgumentException("Ids do not match ");
            }
            final var productCategoryEntities = productMapper.mapCategories(categoryIds, productCreated, userId);

            if (!productCategoryEntities.isEmpty()) {
                productCategoryRepository.saveAll(productCategoryEntities);
            }
        }

        final var occasionIds = productCreateRequestDTO.getOccasionIds();

        if (occasionIds != null && !occasionIds.isEmpty()) {
            final var count = occasionRepository.countByIdIn(occasionIds);
            if (count != occasionIds.size()) {
                throw new IllegalArgumentException("Ids do not match ");
            }
            final var productOccasionEntities = productMapper.mapOccasions(occasionIds, productCreated, userId);

            if (!productOccasionEntities.isEmpty()) {
                productOccasionRepository.saveAll(productOccasionEntities);
            }
        }
        final var productImages = productCreateRequestDTO.getProductImages();
        if (!productImages.isEmpty()) {

            final var productImagesEntities = productImages.stream()
                    .map(productMapper::productImageRequestDTOToProductImageEntity)
                    .peek(productImageEntity -> productImageEntity.setProductEntity(productCreated))
                    .collect(Collectors.toSet());

            productImageRepository.saveAll(productImagesEntities);

        }

        return productMapper.productEntityToProductCreatedResponseDTO(productCreated);

    }

    @Override
    public ProductCategoriesUpdatedResponseDTO updateCategories(UUID id, ProductCategoriesUpdateRequestDTO productCategoriesUpdateRequestDTO) {

        // check the user
        final var userId = productCategoriesUpdateRequestDTO.getUserId();
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        // category ids from request
        final var newCategoryIds = productCategoriesUpdateRequestDTO.getCategoryIds();

        // verify if the categories exist in the database
        final var countCategories = categoryRepository.countByIdIn(newCategoryIds);
        if (newCategoryIds.size() != countCategories) {
            throw new IllegalArgumentException("Ids do not match ");
        }

        final var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + ", not found"));

        final var currentProductCategories = productEntity.getProductCategories();

        // keep the older categories if this match with the new
        final var existingCategoryIdsToKeep = currentProductCategories.stream()
                .filter(cpc -> newCategoryIds.contains(cpc.getCategory().getId()))
                .map(cpc -> cpc.getCategory().getId())
                .collect(Collectors.toSet());

        // delete the another categories
        currentProductCategories.removeIf(pce -> !newCategoryIds.contains(pce.getCategory().getId()));

        final var toAdd = newCategoryIds.stream()
                .filter(nci -> !existingCategoryIdsToKeep.contains(nci))
                .map(newCategoryId ->
                        ProductCategoryEntity.builder()
                                .productCategoryId(ProductCategoryId.builder()
                                        .productId(productEntity.getId())
                                        .categoryId(newCategoryId)
                                        .build())
                                .category(CategoryEntity.builder().id(newCategoryId).build())
                                .product(productEntity)
                                .addedBy(UserEntity.builder().id(userId).build())
                                .build())
                .collect(Collectors.toSet());

        currentProductCategories.addAll(toAdd);

        final var productUpdated = productRepository.save(productEntity);

        return productMapper.productEntityToProductCategoriesUpdatedResponseDTO(productUpdated);
    }

    public ProductOccasionsUpdatedResponseDTO updateOccasions(UUID id, ProductOccasionsUpdateRequestDTO productOccasionsUpdateRequestDTO) {

        final var userId = productOccasionsUpdateRequestDTO.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        final var newOccasionIds = productOccasionsUpdateRequestDTO.getOccasionsIds();

        final var countOccasions = occasionRepository.countByIdIn(newOccasionIds);

        if (newOccasionIds.size() != countOccasions) {
            throw new IllegalArgumentException("Ids do not match ");
        }

        final var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + ", not found"));

        final var currentProductOccasions = productEntity.getProductOccasions();

        Set<Integer> toKeepDatabase = new HashSet<>();
        Set<Integer> toRemoveDatabase = new HashSet<>();

        currentProductOccasions.stream()
                .map(occasionEntity -> occasionEntity.getOccasion().getId())
                .forEach(occasionId -> {
                    if (newOccasionIds.contains(occasionId)) {
                        toKeepDatabase.add(occasionId);
                    } else {
                        toRemoveDatabase.add(occasionId);
                    }
                });

        currentProductOccasions.removeIf(cpo -> toRemoveDatabase.contains(cpo.getOccasion().getId()));

        final var toAdd = newOccasionIds.stream()
                .filter(noi -> !toKeepDatabase.contains(noi))
                .map(occasionId ->
                        ProductOccasionEntity.builder()
                                .productOccasionId(
                                        ProductOccasionId
                                                .builder()
                                                .productId(productEntity.getId())
                                                .occasionId(occasionId)
                                                .build()
                                )
                                .occasion(OccasionEntity.builder().id(occasionId).build())
                                .product(productEntity)
                                .addedBy(UserEntity.builder().id(userId).build())
                                .build())
                .collect(Collectors.toSet());

        currentProductOccasions.addAll(toAdd);

        final var productUpdated = productRepository.save(productEntity);

        return productMapper.productEntityToProductOccasionsUpdatedResponseDTO(productUpdated);

    }

    @Override
    public ProductResponseDTO update(UUID id, ProductUpdateRequestDTO productUpdateRequestDTO) {
        final var userId = productUpdateRequestDTO.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        final var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + ", not found"));

        productUpdateMapper.productUpdateRequestDTOToProductEntity(
                productUpdateRequestDTO,
                productEntity
        );

        productEntity.setUser(UserEntity.builder().id(userId).build());

        final var productUpdated = productRepository.save(productEntity);

        return productMapper.productEntityToProductResponseDTO(productUpdated);
    }

    @Override
    public void delete(UUID id) {

        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with id: " + id + ", not found");
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductImagesUpdatedResponseDTO updateImages(UUID id, ProductImagesUpdateRequestDTO productImagesUpdateRequestDTO) {

        final var userId = productImagesUpdateRequestDTO.getUserId();

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User Id " + userId + ", not found");
        }

        final var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + ", not found"));

        final var currentProductImages = productEntity.getProductImages();

        final var newImages = productImagesUpdateRequestDTO.getImages();

        Set<ProductImageEntity> toKeepDatabase = new HashSet<>();
        Set<ProductImageEntity> toRemoveDatabase = new HashSet<>();

        Map<Integer, ProductImageUpdateRequestDTO> imageDto = newImages.stream()
                .filter(p -> p.getId() != null)
                .collect(Collectors.toMap(ProductImageUpdateRequestDTO::getId, Function.identity())); //p->p

        currentProductImages.forEach(imageEntity -> {

            final var dto = imageDto.get(imageEntity.getId());

            if (dto == null) {
               toRemoveDatabase.add(imageEntity);
            } else {
                imageEntity.setUrl(dto.getUrl());
                imageEntity.setAltText(dto.getAltText());
                imageEntity.setProductEntity(productEntity);
                toKeepDatabase.add(imageEntity);
            }
//            newImages.stream()
//                    .filter(p -> p.getId() != null && p.getId().equals(imageEntity.getId()))
//                    .findFirst()
//                    .ifPresentOrElse(dto -> {
//                        imageEntity.setUrl(dto.getUrl());
//                        imageEntity.setAltText(dto.getAltText());
//                        imageEntity.setProductEntity(productEntity);
//                        toKeepDatabase.add(imageEntity);
//                    }, () -> toRemoveDatabase.add(imageEntity));

        });



        currentProductImages.removeIf(cpi -> toRemoveDatabase.stream().anyMatch(p -> p.getId().equals(cpi.getId())));


        final var toAdd = newImages.stream()
                .filter(nei -> toKeepDatabase.stream().noneMatch(p -> p.getId().equals(nei.getId())))
                .map(nei -> ProductImageEntity.builder()
                        .altText(nei.getAltText())
                        .url(nei.getUrl())
                        .productEntity(productEntity)
                        .build()
                )
                .collect(Collectors.toSet());

        currentProductImages.addAll(toAdd);

        final var productUpdated = productRepository.save(productEntity);

        return productMapper.productEntityToProductImagesUpdatedResponseDTO(productUpdated);
    }

//    public <ID, ENTITY> void validateAndSetRelation(
//            String module,
//            Set<ID> ids,
//            Function<Set<ID>, Long> countFunction,
//            BiFunction<Set<ID>, ProductEntity, Set<ENTITY>> mappingFunction,
//            Consumer<Set<ENTITY>> persistFunction,
//            ProductEntity productEntity
//            ) {
//        if (ids != null && !ids.isEmpty()) {
//            Long count = countFunction.apply(ids);
//            if (count != ids.size()) {
//                throw new IllegalArgumentException(module + ": Ids do not match ");
//            }
//
//            Set<ENTITY> entities = mappingFunction.apply(ids, productEntity);
//            if (!entities.isEmpty()) {
//                persistFunction.accept(entities);
//            }
//
//        }
//    }
}
