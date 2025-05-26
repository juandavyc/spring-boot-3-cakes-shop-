package com.juandavyc.bakery.entity;

import com.juandavyc.bakery.entity.embeddable.OrderProductId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_product")

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductEntity {

    @EmbeddedId
    private OrderProductId orderProductId;

    @Column(nullable = false)
    private BigInteger subtotal;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


}
