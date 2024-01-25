/**
 * User Roles DB Model
 */

package com.project.application.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLES")
public class UserRoles {
    //User Roles DB Model Class
    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique=true)
    private String role;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<AdminRoles> adminRoles;

    //Getter and Setters
}
