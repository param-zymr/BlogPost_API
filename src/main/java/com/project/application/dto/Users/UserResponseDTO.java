package com.project.application.dto.Users;

import com.project.application.dto.UserRoles.UserRolesResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String profilePhoto;
    private UserRolesResponseDTO userRole;
}
