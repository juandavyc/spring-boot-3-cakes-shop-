package com.juandavyc.bakery.dto.occasion.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class OccasionCreateRequestDTO {

    private String name;

}
