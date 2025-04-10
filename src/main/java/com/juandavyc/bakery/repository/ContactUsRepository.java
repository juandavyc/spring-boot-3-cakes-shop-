package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.dto.contactus.response.ContactUsResponseDTO;
import com.juandavyc.bakery.entity.ContactUsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface ContactUsRepository extends
        JpaRepository<ContactUsEntity, Integer>,
        JpaSpecificationExecutor<ContactUsEntity> {

    @NonNull
    Page<ContactUsEntity> findAll(Specification<ContactUsEntity> specification, @NonNull Pageable pageable);

}
