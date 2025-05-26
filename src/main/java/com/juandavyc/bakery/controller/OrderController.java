package com.juandavyc.bakery.controller;

import com.juandavyc.bakery.dto.order.request.OrderProductRequestDTO;
import com.juandavyc.bakery.dto.order.request.OrderRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderProductResponseDTO;
import com.juandavyc.bakery.dto.order.response.OrderResponseDTO;
import com.juandavyc.bakery.entity.embeddable.OrderProductId;
import com.juandavyc.bakery.service.order.OrderService;
import com.juandavyc.bakery.service.order.product.OrderProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderResponseDTO> getById(
            @PathVariable UUID id
    ) {

        final var order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }


    @PostMapping()
    public ResponseEntity<String> create(
            @Valid @RequestBody OrderRequestDTO orderRequestDTO
    ) {
        final var orderId = orderService.create(orderRequestDTO);

        // return ResponseEntity.ok(created);

        return ResponseEntity.created(
                URI.create(("/orders/").concat(orderId.toString()))
        ).build();
    }

    @PostMapping(path = "{id}/product")
    public ResponseEntity<OrderProductResponseDTO> createProduct(
            @PathVariable UUID id,
            @Valid @RequestBody OrderProductRequestDTO orderProductRequestDTO
    ) {

        final var created = orderProductService.create(id, orderProductRequestDTO);
        return ResponseEntity.ok(created);
    }


}
