package com.juandavyc.bakery.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Builder

public class ProductOccasionId {

    private UUID productId;
    private Integer occasionId;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProductOccasionId that = (ProductOccasionId) object;
        return Objects.equals(productId, that.productId) && Objects.equals(occasionId, that.occasionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, occasionId);
    }

}
