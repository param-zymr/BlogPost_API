/**
 * Admin Roles DB Model
 */

package com.project.application.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ADMIN_ROLES")
public class AdminRoles {
    //Admin Roles DB Model Class
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRoles userRole;

    //Constructors, Getter and Setters
}
