package com.juandavyc.bakery.service.category;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.category.request.CategoryCreateRequestDTO;
import com.juandavyc.bakery.dto.category.response.CategoryCreatedResponseDTO;
import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.mapper.category.CategoryMapper;
import com.juandavyc.bakery.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageMapper pageMapper;

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public PageResponse<CategoryResponseDTO> getCategoriesPage(Pageable pageable) {

        final var categoryEntities = categoryRepository.findAll(pageable);

        return pageMapper.toPageResponse(categoryEntities, categoryMapper::categoryEntityToCategoryResponseDTO);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public CategoryResponseDTO getCategoriesById(Integer id) {

        final var categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return categoryMapper.categoryEntityToCategoryResponseDTO(categoryEntity);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public CategoryResponseDTO getCategoriesBySlug(String slug) {

        final var categoryEntity = categoryRepository.findBySlug(slug);

        if (categoryEntity == null) {
            throw new RuntimeException("Category not found");
        }

        return categoryMapper.categoryEntityToCategoryResponseDTO(categoryEntity);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryCreatedResponseDTO create(CategoryCreateRequestDTO categoryCreateRequestDTO) {

        final var categoryEntity = categoryMapper
                .categoryCreateRequestDTOToCategoryEntity(categoryCreateRequestDTO);

        categoryEntity.setSlug(SlugUtil.toSlug(categoryCreateRequestDTO.getName()));

        final var categoryCreated = categoryRepository.save(categoryEntity);

        return categoryMapper.categoryEntityToCategoryCreatedResponseDTO(categoryCreated);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {

        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);

    }


}
