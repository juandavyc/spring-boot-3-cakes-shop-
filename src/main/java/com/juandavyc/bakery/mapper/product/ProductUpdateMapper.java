package com.juandavyc.bakery.mapper.product;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.product.request.ProductUpdateRequestDTO;
import com.juandavyc.bakery.entity.ProductEntity;
import jakarta.persistence.Table;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductUpdateMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "slug", source = "name", qualifiedByName = "toSlug")

    void productUpdateRequestDTOToProductEntity(
            ProductUpdateRequestDTO productUpdateRequestDTO,
            @MappingTarget ProductEntity productEntity);




    @Named("toSlug")
    default String toSlug(String name){
        return SlugUtil.toSlug(name);
    }

}
