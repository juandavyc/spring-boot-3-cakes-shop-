package com.juandavyc.bakery.service.occasion;

import com.juandavyc.bakery.dto.occasion.request.OccasionCreateRequestDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionCreatedResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OccasionService {

    PageResponse<OccasionResponseDTO> getCategoriesPage(Pageable pageable);

    OccasionResponseDTO getCategoriesById(Integer id);

    OccasionResponseDTO getCategoriesBySlug(String slug);

    OccasionCreatedResponseDTO create(OccasionCreateRequestDTO occasionCreateRequestDTO);

    List<OccasionCreatedResponseDTO> createBatch(List<String> occasionsToCreateRequestDTO);

    void deleteById(Integer id);


}
