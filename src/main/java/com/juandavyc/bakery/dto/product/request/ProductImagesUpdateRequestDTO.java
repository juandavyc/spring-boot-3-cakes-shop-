package com.juandavyc.bakery.dto.product.request;

import com.juandavyc.bakery.dto.productimage.request.ProductImageCreateRequestDTO;
import com.juandavyc.bakery.dto.productimage.request.ProductImageUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ProductImagesUpdateRequestDTO {

    private Set<ProductImageUpdateRequestDTO> images;
    public Integer userId;

}
