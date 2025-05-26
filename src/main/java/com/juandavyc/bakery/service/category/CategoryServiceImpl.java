package com.juandavyc.bakery.service.category;

import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.category.request.CategoryCreateRequestDTO;
import com.juandavyc.bakery.dto.category.response.CategoryCreatedResponseDTO;
import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.entity.CategoryEntity;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.mapper.category.CategoryMapper;
import com.juandavyc.bakery.repository.CategoryRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        final var categoryCreated = categoryRepository.save(categoryEntity);

        return categoryMapper.categoryEntityToCategoryCreatedResponseDTO(categoryCreated);
    }

    @Override
    public List<CategoryCreatedResponseDTO> createBatch(List<String> categoriesToCreate) {

         final var entities = categoriesToCreate.stream()
                .map(categoryMapper::stringToCategoryEntity)
                .toList();

        final var entitiesCreated = categoryRepository.saveAll(entities);

        return entitiesCreated.stream()
                .map(categoryMapper::categoryEntityToCategoryCreatedResponseDTO)
                .toList();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {

        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);

    }


}
