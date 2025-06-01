package com.juandavyc.bakery.controller;


import com.juandavyc.bakery.dto.occasion.request.OccasionCreateRequestDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionCreatedResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.service.occasion.OccasionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/occasions")
@RequiredArgsConstructor
public class OccasionController {

    private final OccasionService occasionService;

    @GetMapping
    public ResponseEntity<PageResponse<OccasionResponseDTO>> getCategoriesPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(occasionService.getCategoriesPage(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OccasionResponseDTO> getCategoriesById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(occasionService.getCategoriesById(id));
    }

    @GetMapping(path = "/slug/{slug}")
    public ResponseEntity<OccasionResponseDTO> getCategoriesBySlug(
            @PathVariable String slug
    ) {
        return ResponseEntity.ok(occasionService.getCategoriesBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<OccasionCreatedResponseDTO> create(
            @RequestBody OccasionCreateRequestDTO occasionCreateRequestDTO
    ) {
        final var occasionCreated = occasionService.create(occasionCreateRequestDTO);
        return ResponseEntity
                .created(URI.create("/api/occasions/" + occasionCreated.id()))
                .body(occasionCreated);
    }

    @PostMapping(path = "batch")
    public ResponseEntity<List<OccasionCreatedResponseDTO>> createBatch(
            @RequestBody List<String> occasionsToCreateRequestDTO
    ) {
        final var occasionsCreated = occasionService.createBatch(occasionsToCreateRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(occasionsCreated);
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        occasionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
