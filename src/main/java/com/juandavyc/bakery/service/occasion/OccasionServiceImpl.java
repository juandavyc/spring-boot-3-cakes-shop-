package com.juandavyc.bakery.service.occasion;


import com.juandavyc.bakery.common.util.SlugUtil;
import com.juandavyc.bakery.dto.occasion.request.OccasionCreateRequestDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionCreatedResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.mapper.user.OccasionMapper;
import com.juandavyc.bakery.repository.OccasionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OccasionServiceImpl implements OccasionService {

    private final OccasionRepository occasionRepository;
    private final OccasionMapper occasionMapper;
    private final PageMapper pageMapper;

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public PageResponse<OccasionResponseDTO> getCategoriesPage(Pageable pageable) {

        final var occasionEntities = occasionRepository.findAll(pageable);

        return pageMapper.toPageResponse(occasionEntities, occasionMapper::occasionEntityToOccasionResponseDTO);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public OccasionResponseDTO getCategoriesById(Integer id) {

        final var occasionEntity = occasionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occasion not found"));

        return occasionMapper.occasionEntityToOccasionResponseDTO(occasionEntity);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public OccasionResponseDTO getCategoriesBySlug(String slug) {

        final var occasionEntity = occasionRepository.findBySlug(slug);

        if (occasionEntity == null) {
            throw new RuntimeException("Occasion not found");
        }

        return occasionMapper.occasionEntityToOccasionResponseDTO(occasionEntity);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public OccasionCreatedResponseDTO create(OccasionCreateRequestDTO occasionCreateRequestDTO) {

        final var occasionEntity = occasionMapper
                .occasionCreateRequestDTOToOccasionEntity(occasionCreateRequestDTO);

        occasionEntity.setSlug(SlugUtil.toSlug(occasionCreateRequestDTO.getName()));

        final var occasionCreated = occasionRepository.save(occasionEntity);

        return occasionMapper.occasionEntityToOccasionCreatedResponseDTO(occasionCreated);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {

        if (!occasionRepository.existsById(id)) {
            throw new IllegalArgumentException("Occasion with id " + id + " not found");
        }
        occasionRepository.deleteById(id);

    }


}
