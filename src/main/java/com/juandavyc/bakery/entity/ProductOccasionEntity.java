package com.juandavyc.bakery.entity;

import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product_occasion")

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOccasionEntity {

    @EmbeddedId
    private ProductOccasionId productOccasionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "occasionId")
    @JoinColumn(name = "occasion_id")
    @ToString.Exclude
    private OccasionEntity occasion;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity addedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProductOccasionEntity that = (ProductOccasionEntity) object;
        return Objects.equals(productOccasionId, that.productOccasionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productOccasionId);
    }
}
