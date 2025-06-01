package com.juandavyc.bakery.controller.open;

import com.juandavyc.bakery.dto.contactus.request.ContactUsCreateRequestDTO;
import com.juandavyc.bakery.service.contactus.ContactUsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/public/contact-us")
@RequiredArgsConstructor
public class ContactUsPublicController {

    private final ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<Void> create (
            @Valid @RequestBody ContactUsCreateRequestDTO contactUsCreateRequestDTO
    ){
        contactUsService.create(contactUsCreateRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
