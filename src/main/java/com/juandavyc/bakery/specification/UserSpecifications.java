package com.juandavyc.bakery.specification;

import com.juandavyc.bakery.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<UserEntity> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<UserEntity> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                username == null ? null : criteriaBuilder.equal(root.get("username"), username.toLowerCase());
    }

    public static Specification<UserEntity> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.equal(root.get("email"), email.toLowerCase());
    }

}
