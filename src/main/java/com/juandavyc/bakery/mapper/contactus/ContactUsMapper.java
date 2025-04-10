package com.juandavyc.bakery.mapper.contactus;

import com.juandavyc.bakery.dto.contactus.request.ContactUsCreateRequestDTO;
import com.juandavyc.bakery.dto.contactus.response.ContactUsResponseDTO;
import com.juandavyc.bakery.entity.ContactUsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactUsMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ContactUsEntity contactUsCreateRequestDTOToContactUsEntity(
            ContactUsCreateRequestDTO contactUsCreateRequestDTO
    );

    ContactUsResponseDTO contactUsEntityToContactUsResponseDTO(
            ContactUsEntity contactUsEntity);


}
