package com.project.application.repositories;

import com.project.application.entities.user.AdminRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRolesRepositories extends CrudRepository<AdminRoles, Integer> {

    //Check if user email is mentioned in admin users table
    @Query(value = "SELECT * FROM admin_roles WHERE admin_roles.email = :userEmail",
            nativeQuery = true)
    Optional<AdminRoles> findByEmail(@Param("userEmail") String email);

}
