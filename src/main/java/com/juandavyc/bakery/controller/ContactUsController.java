package com.juandavyc.bakery.controller;


import com.juandavyc.bakery.dto.contactus.request.ContactUsCreateRequestDTO;
import com.juandavyc.bakery.dto.contactus.response.ContactUsResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.service.contactus.ContactUsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = "/api/contact-us")
@RequiredArgsConstructor
public class ContactUsController {

    private final ContactUsService contactUsService;

    @GetMapping
    public ResponseEntity<PageResponse<ContactUsResponseDTO>> getContactUsPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            ) {
        return ResponseEntity.ok(contactUsService.getContactUsPage(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactUsResponseDTO> getContactUsId(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(contactUsService.getContactUsId(id));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PageResponse<ContactUsResponseDTO>> search(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String message,
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                contactUsService.getContactUsSearch(
                        id,
                        name,
                        phoneNumber,
                        message,
                        pageable
                )
        );
    }

    @PostMapping
    public ResponseEntity<Void> create (
            @Valid @RequestBody ContactUsCreateRequestDTO contactUsCreateRequestDTO
    ){
        contactUsService.create(contactUsCreateRequestDTO);
        return ResponseEntity.noContent().build();
    }


}
