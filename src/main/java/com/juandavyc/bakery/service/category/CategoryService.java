package com.juandavyc.bakery.service.category;

import com.juandavyc.bakery.dto.category.request.CategoryCreateRequestDTO;
import com.juandavyc.bakery.dto.category.response.CategoryCreatedResponseDTO;
import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    PageResponse<CategoryResponseDTO> getCategoriesPage(Pageable pageable);

    CategoryResponseDTO getCategoriesById(Integer id);

    CategoryResponseDTO getCategoriesBySlug(String slug);

    CategoryCreatedResponseDTO create(CategoryCreateRequestDTO categoryCreateRequestDTO);

    void deleteById(Integer id);
}
