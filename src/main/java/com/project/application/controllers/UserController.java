/**
 * User Controller
 */

package com.project.application.controllers;

import com.project.application.dto.UserRoles.UserRolesResponseDTO;
import com.project.application.dto.Users.UserRequestDTO;
import com.project.application.dto.Users.UserResponseDTO;
import com.project.application.dto.Users.UserUpdateRequestDTO;
import com.project.application.dto.response.GeneralResponse;
import com.project.application.mappers.UserMapper;
import com.project.application.mappers.UserRolesMapper;
import com.project.application.entities.user.AdminRoles;
import com.project.application.entities.user.User;
import com.project.application.entities.user.UserRoles;
import com.project.application.repositories.AdminRolesRepositories;
import com.project.application.repositories.UserRepositories;
import com.project.application.repositories.UserRoleRepositories;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.project.application.utils.SecurityPassword;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UserController {
    //User Operations Routes

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserRoleRepositories userRoleRepositories;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private AdminRolesRepositories adminRolesRepositories;

    // Get list of all users
    @GetMapping(path = "/all")
    public ResponseEntity<GeneralResponse> getAllUsers() {
        ArrayList<UserResponseDTO> userList = new ArrayList<>();
        Iterable<User> users = userRepositories.findAllUsers();
        if (users != null){
            for (User user : users) {
                UserRolesResponseDTO userRoleResponse = userRolesMapper.userRolesToUserRolesResponseDTO(user.getUserRole());
                UserResponseDTO userResponseDTOObject = userMapper.userToUserResponseDTO(user, userRoleResponse);
                userList.add(userResponseDTOObject);
            }
        }
        log.info("User list fetched successfully");
        return new ResponseEntity<>(new GeneralResponse(2000, "User list fetched successfully", userList), HttpStatus.OK);
    }

    // Get user details by ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<GeneralResponse> getUser(@NotNull(message = "Provide user id")
                                              @PathVariable("id") Integer userId) {
        User userData;
        Optional<User> dbUser = userRepositories.findUserById(userId);
        if (dbUser.isPresent()) {
            userData = dbUser.get();
            UserRolesResponseDTO userRoleResponse = userRolesMapper.userRolesToUserRolesResponseDTO(userData.getUserRole());
            UserResponseDTO userResponseDTOObject = userMapper.userToUserResponseDTO(userData, userRoleResponse);
            log.info("User details fetched successfully");
            return new ResponseEntity<>(new GeneralResponse(2000, "User data fetched successfully", userResponseDTOObject), HttpStatus.OK);
        }
        log.error("Invalid user ID");
        return new ResponseEntity<>(new GeneralResponse(4000, "User not found"), HttpStatus.OK);
    }

    // Add user
    @Transactional
    @PostMapping(path = "/add")
    public ResponseEntity<GeneralResponse> addNewUser(@Valid @RequestBody UserRequestDTO newUser) {
        Optional<User> checkUser = userRepositories.checkEmail(newUser.getEmail());
        if (!checkUser.isPresent()) {
            String user_role = "user";
            Optional<AdminRoles> adminRoles = adminRolesRepositories.findByEmail(newUser.getEmail());
            if (adminRoles.isPresent()) {
                user_role = "admin";
            }
            Optional<UserRoles> userRole = userRoleRepositories.findRoleByName(user_role);
            User newDbUser = userMapper.userRequestDTOToUser(newUser, userRole.orElseThrow());
            newDbUser.setPassword(SecurityPassword.hashPassword(newUser.getPassword()));
            newDbUser.setCreatedOn(Instant.now());
            newDbUser.setUpdatedOn(Instant.now());
            newDbUser.setIsDeleted(Boolean.FALSE);
            userRepositories.save(newDbUser);
            UserRolesResponseDTO userRoleResponse = userRolesMapper.userRolesToUserRolesResponseDTO(newDbUser.getUserRole());
            UserResponseDTO userResponse = userMapper.userToUserResponseDTO(newDbUser, userRoleResponse);
            log.info(String.format("New user created successfully with email ID: %s", userResponse.getEmail()));
            return new ResponseEntity<>(new GeneralResponse(2000, "User created successfully", userResponse), HttpStatus.OK);
        }
        log.error("Invalid user data");
        return new ResponseEntity<>(new GeneralResponse(4000, "Invalid user data"), HttpStatus.BAD_REQUEST);
    }

    // Update user
    @Transactional
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<GeneralResponse> UpdateUser(@NotNull(message = "Provide user id")
                                                 @PathVariable("id") Integer userId,
                                            @Valid @RequestBody UserUpdateRequestDTO newUser) {
        User userData;
        Optional<User> dbUser = userRepositories.findUserById(userId);
        if (dbUser.isPresent()){
            userData = dbUser.get();
            if (userRepositories.checkUserData(userId, newUser.getEmail()).isEmpty()){
                userData.setName(newUser.getName());
                userData.setEmail(newUser.getEmail());
                userData.setProfilePhoto(newUser.getProfilePhoto());
                userRepositories.save(userData);
                UserRolesResponseDTO userRoleResponse = userRolesMapper.userRolesToUserRolesResponseDTO(userData.getUserRole());
                UserResponseDTO userResponse = userMapper.userToUserResponseDTO(userData, userRoleResponse);
                log.info("User info updated successfully");
                return new ResponseEntity<>(new GeneralResponse(2000, "User updated successfully", userResponse), HttpStatus.OK);
            }
            log.error("Invalid user data");
        }
        log.error("Invalid user ID");
        return new ResponseEntity<>(new GeneralResponse(4000, "Invalid user ID or data"), HttpStatus.BAD_REQUEST);
    }


    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteUser(@NotNull(message = "Provide user id")
                                                 @PathVariable("id") Integer userId) {
        if (userRepositories.findUserById(userId).isPresent()) {
            userRepositories.deleteUserById(userId);
            log.info("User marked deleted successfully");
            return new ResponseEntity<>(new GeneralResponse(2000, "User deleted successfully"), HttpStatus.OK);
        }
        log.error("Invalid user ID");
        return new ResponseEntity<>(new GeneralResponse(4000, "User not found"), HttpStatus.OK);
    }
}
