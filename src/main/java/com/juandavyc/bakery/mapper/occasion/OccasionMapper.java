package com.juandavyc.bakery.mapper.occasion;


import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.occasion.request.OccasionCreateRequestDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionCreatedResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.entity.OccasionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OccasionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", source = "name", qualifiedByName = "toSlug")
    @Mapping(target = "productOccasions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OccasionEntity occasionCreateRequestDTOToOccasionEntity(OccasionCreateRequestDTO occasionCreateRequestDTO);

    OccasionResponseDTO occasionEntityToOccasionResponseDTO(OccasionEntity occasionEntity);

    OccasionCreatedResponseDTO occasionEntityToOccasionCreatedResponseDTO(OccasionEntity occasionEntity);

    default OccasionEntity stringToOccasionEntity(String name) {
        return OccasionEntity.builder()
                .name(name)
                .slug(toSlug(name))
                .build();
    }


    @Named(value = "toSlug")
    default String toSlug(String name) {
        return SlugUtil.toSlug(name);
    }

}
