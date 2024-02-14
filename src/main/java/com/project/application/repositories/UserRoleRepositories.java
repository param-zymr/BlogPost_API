package com.project.application.repositories;

import com.project.application.entities.user.UserRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepositories extends CrudRepository<UserRoles, Integer> {
    @Query(value = "SELECT * FROM user_roles as roles WHERE roles.role = :roleName",
            nativeQuery = true)
    Optional<UserRoles> findRoleByName(@Param("roleName") String name);
}
