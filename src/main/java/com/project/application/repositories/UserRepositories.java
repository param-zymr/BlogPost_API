/**
 * User DB Repositories
 */

package com.project.application.repositories;

import com.project.application.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositories extends CrudRepository<User, Integer> {
    //User Repositories Interface
    
}
