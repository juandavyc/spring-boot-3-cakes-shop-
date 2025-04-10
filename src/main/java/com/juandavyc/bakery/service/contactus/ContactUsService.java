package com.juandavyc.bakery.service.contactus;

import com.juandavyc.bakery.dto.contactus.request.ContactUsCreateRequestDTO;
import com.juandavyc.bakery.dto.contactus.response.ContactUsResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.entity.ContactUsEntity;
import org.springframework.data.domain.Pageable;

public interface ContactUsService {

    PageResponse<ContactUsResponseDTO> getContactUsPage(Pageable pageable);

    ContactUsResponseDTO getContactUsId(Integer id);

    PageResponse<ContactUsResponseDTO> getContactUsSearch(
            Integer id,
            String name,
            String phoneNumber,
            String message,
            Pageable pageable
    );

    void create(ContactUsCreateRequestDTO contactUsCreateRequestDTO);

}
