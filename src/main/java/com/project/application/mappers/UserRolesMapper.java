package com.project.application.mappers;

import com.project.application.dto.UserRoles.UserRolesResponseDTO;

import com.project.application.entities.user.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRolesMapper {
    UserRolesResponseDTO userRolesToUserRolesResponseDTO(UserRoles userRoles);
}
