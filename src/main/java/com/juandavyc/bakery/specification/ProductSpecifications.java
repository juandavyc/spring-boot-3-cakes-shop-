package com.juandavyc.bakery.specification;


import com.juandavyc.bakery.entity.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;

public class ProductSpecifications {

    public static Specification<ProductEntity> byName(String name) {
        return (root, query, criteriaBuilder) ->{

            if(name == null || name.trim().isEmpty()) return null;

            final var likePattern = ("%").concat((name).toLowerCase()).concat("%");

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likePattern)
            );
        };
    }

    public static Specification<ProductEntity> byPriceGreaterThan(BigInteger minPrice) {
        return (root, query, criteriaBuilder) ->
                minPrice == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<ProductEntity> byPriceLessThan(BigInteger maxPrice) {
        return (root, query, criteriaBuilder) ->
                maxPrice == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<ProductEntity> byCategorySlug(String slug) {
        return (root, query, criteriaBuilder) ->{
            if(slug == null || slug.trim().isEmpty()) return null;

            Join<ProductEntity, ProductCategoryEntity> productCategoryJoin = root.join("productCategories", JoinType.INNER);
            Join<ProductCategoryEntity, CategoryEntity> categoryJoin = productCategoryJoin.join("category", JoinType.INNER);

            return criteriaBuilder.equal(categoryJoin.get("slug"), slug);
        };
    }

    public static Specification<ProductEntity> byOccasionSlug(String slug) {
        return (root, query, criteriaBuilder) ->{
            if(slug == null || slug.trim().isEmpty()) return null;

            Join<ProductEntity, ProductOccasionEntity> productOccasionJoin = root.join("productOccasions", JoinType.INNER);
            Join<ProductOccasionEntity, OccasionEntity> occasionJoin = productOccasionJoin.join("occasion", JoinType.INNER);

            return criteriaBuilder.equal(occasionJoin.get("slug"), slug);
        };
    }


}
