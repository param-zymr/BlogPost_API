/**
 * User Controller
 */

package com.project.application.controllers;

import com.project.application.repositories.UserRepositories;
import com.project.application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {
    //User Operations Routes

    @Autowired
    private UserRepositories userRepositories;

    @PostMapping(path="/add")
    public @ResponseBody User addNewUser(@RequestBody User newUser){
        userRepositories.save(newUser);
        return newUser;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepositories.findAll();
    }
}
