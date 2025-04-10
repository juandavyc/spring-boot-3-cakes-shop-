package com.juandavyc.bakery.specification;

import com.juandavyc.bakery.entity.ContactUsEntity;
import org.springframework.data.jpa.domain.Specification;

public class ContactUsSpecifications {

    public static Specification<ContactUsEntity> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<ContactUsEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"),
                        ("%").concat(name).concat("%")
                );
    }

    public static Specification<ContactUsEntity> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) ->
                phoneNumber == null ? null : criteriaBuilder.like(root.get("phoneNumber"),
                        ("%").concat(phoneNumber).concat("%")
                );
    }

    public static Specification<ContactUsEntity> hasMessage(String message) {
        return (root, query, criteriaBuilder) ->
                message == null ? null : criteriaBuilder.like(root.get("phoneNumber"),
                        ("%").concat(message).concat("%")
                );
    }



}
