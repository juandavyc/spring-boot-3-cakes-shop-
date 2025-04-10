package com.juandavyc.bakery.dto.category.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class CategoryCreateRequestDTO {
    private String name;
}
