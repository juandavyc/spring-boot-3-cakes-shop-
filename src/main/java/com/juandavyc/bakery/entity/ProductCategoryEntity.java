package com.juandavyc.bakery.entity;

import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product_category")

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryEntity {

    @EmbeddedId
    private ProductCategoryId productCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "categoryId")
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity addedBy;

    @CreationTimestamp
    public LocalDateTime createdAt;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProductCategoryEntity that = (ProductCategoryEntity) object;
        return Objects.equals(productCategoryId, that.productCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productCategoryId);
    }
}


