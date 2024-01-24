/**
 * User DB Model
 */

package com.project.application.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    //User DB Model Class
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    //Getter and Setters
    private String name;

    private String email;

}
