package com.juandavyc.bakery.mapper.category;


import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.category.request.CategoryCreateRequestDTO;
import com.juandavyc.bakery.dto.category.response.CategoryCreatedResponseDTO;
import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug",source = "name", qualifiedByName = "toSlug")
    @Mapping(target = "productCategories", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CategoryEntity categoryCreateRequestDTOToCategoryEntity(CategoryCreateRequestDTO categoryCreateRequestDTO);

    default CategoryEntity stringToCategoryEntity(String name){
        return CategoryEntity.builder()
                .name(name)
                .slug(toSlug(name))
                .build();
    }


    CategoryResponseDTO categoryEntityToCategoryResponseDTO(CategoryEntity categoryEntity);

    CategoryCreatedResponseDTO categoryEntityToCategoryCreatedResponseDTO(CategoryEntity categoryEntity);




    @Named("toSlug")
    default String toSlug(String slug) {
        return SlugUtil.toSlug(slug);
    }


}
