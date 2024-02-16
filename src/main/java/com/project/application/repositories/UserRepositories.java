/**
 * User DB Repositories
 */

package com.project.application.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.project.application.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepositories extends CrudRepository<User, Integer> {
    //User Repositories Interface

    @Query(value = "SELECT * FROM users as usr WHERE usr.is_deleted = false",
            nativeQuery = true)
    Iterable<User> findAllUsers();

    @Query(value = "SELECT * FROM users as usr WHERE usr.id = :userId AND usr.is_deleted = false",
            nativeQuery = true)
    Optional<User> findUserById(@Param("userId") Integer id);

    @Query(value = "SELECT * FROM users as usr WHERE usr.email = :userEmail",
            nativeQuery = true)
    Optional<User> checkEmail(@Param("userEmail") String email);

    @Query(value = "SELECT * FROM users as usr WHERE usr.email = :userEmail " +
            "AND usr.id != :userId", nativeQuery = true)
    Optional<User> checkUserData(@Param("userId") Integer id, @Param("userEmail") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET is_deleted = true WHERE id = :userId",
            nativeQuery = true)
    void deleteUserById(@Param("userId") Integer id);
}
