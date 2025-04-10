package com.juandavyc.bakery.mapper.user;


import com.juandavyc.bakery.dto.occasion.request.OccasionCreateRequestDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionCreatedResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.entity.OccasionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OccasionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OccasionEntity occasionCreateRequestDTOToOccasionEntity(OccasionCreateRequestDTO occasionCreateRequestDTO);

    OccasionResponseDTO occasionEntityToOccasionResponseDTO(OccasionEntity occasionEntity);

    OccasionCreatedResponseDTO occasionEntityToOccasionCreatedResponseDTO(OccasionEntity occasionEntity);

}
