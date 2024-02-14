package com.project.application.mappers;

import com.project.application.dto.UserRoles.UserRolesResponseDTO;
import com.project.application.dto.Users.UserResponseDTO;
import com.project.application.entities.user.User;
import com.project.application.dto.Users.UserRequestDTO;
import com.project.application.entities.user.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userRole", expression = "java(userRoleObject)")
    })
    User userRequestDTOToUser(UserRequestDTO userRequestDTO, UserRoles userRoleObject);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "userRole", expression = "java(userRoleDTOObject)")
    })
    UserResponseDTO userToUserResponseDTO(User user, UserRolesResponseDTO userRoleDTOObject);

}
