package com.juandavyc.bakery.service.contactus;

import com.juandavyc.bakery.dto.contactus.request.ContactUsCreateRequestDTO;
import com.juandavyc.bakery.dto.contactus.response.ContactUsResponseDTO;
import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.entity.ContactUsEntity;
import com.juandavyc.bakery.mapper.contactus.ContactUsMapper;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.repository.ContactUsRepository;
import com.juandavyc.bakery.specification.ContactUsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final ContactUsMapper contactUsMapper;
    private final PageMapper pageMapper;

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public PageResponse<ContactUsResponseDTO> getContactUsPage(Pageable pageable) {

        final var contactUs = contactUsRepository.findAll(pageable);
        return pageMapper.toPageResponse(contactUs, contactUsMapper::contactUsEntityToContactUsResponseDTO);

    }

    @Transactional(propagation = Propagation.NESTED,readOnly = true)
    public ContactUsResponseDTO getContactUsId(Integer id) {

        final var contactUs = contactUsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Contact Us with id "+id+",not found"));

        return contactUsMapper.contactUsEntityToContactUsResponseDTO(contactUs);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageResponse<ContactUsResponseDTO> getContactUsSearch(
            Integer id,
            String name,
            String phoneNumber,
            String message,
            Pageable pageable
    ) {

            Specification<ContactUsEntity> specification = Specification
                    .where(ContactUsSpecifications.hasId(id))
                    .and(ContactUsSpecifications.hasName(name))
                    .and(ContactUsSpecifications.hasPhoneNumber(phoneNumber))
                    .and(ContactUsSpecifications.hasMessage(message));

            final var contactUs = contactUsRepository.findAll(specification, pageable);

        return pageMapper.toPageResponse(contactUs,contactUsMapper::contactUsEntityToContactUsResponseDTO);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void create(ContactUsCreateRequestDTO contactUsCreateRequestDTO) {

        final var contactUsEntity = contactUsMapper
                .contactUsCreateRequestDTOToContactUsEntity(contactUsCreateRequestDTO);

        contactUsRepository.save(contactUsEntity);

    }





}
