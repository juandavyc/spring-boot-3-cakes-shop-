package com.juandavyc.bakery.mapper.user;

import com.juandavyc.bakery.dto.permission.response.PermissionResponseDTO;
import com.juandavyc.bakery.dto.role.RoleResponseDTO;
import com.juandavyc.bakery.dto.user.request.UserCreateRequestDTO;
import com.juandavyc.bakery.dto.user.request.UserUpdateRequestDTO;
import com.juandavyc.bakery.dto.user.response.UserCreatedResponseDTO;
import com.juandavyc.bakery.dto.user.response.UserResponseDTO;
import com.juandavyc.bakery.entity.PermissionEntity;
import com.juandavyc.bakery.entity.RoleEntity;
import com.juandavyc.bakery.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity userRequestDTOToUserEntity(UserCreateRequestDTO userCreateRequestDTO);

    UserResponseDTO userEntityToUserResponseDTO(UserEntity userEntity);

    UserCreatedResponseDTO userEntityToUserCreatedResponseDTO(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

    void updateUserFromDto(UserUpdateRequestDTO userUpdateRequestDTO, @MappingTarget UserEntity userEntity);


}
