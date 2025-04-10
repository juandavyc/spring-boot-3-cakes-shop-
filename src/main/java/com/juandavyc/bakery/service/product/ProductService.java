package com.juandavyc.bakery.service.product;

import com.juandavyc.bakery.dto.product.request.ProductCreateRequestDTO;
import com.juandavyc.bakery.dto.product.response.ProductCreatedResponseDTO;

public interface ProductService {


    ProductCreatedResponseDTO create(ProductCreateRequestDTO productCreateRequestDTO);
}
