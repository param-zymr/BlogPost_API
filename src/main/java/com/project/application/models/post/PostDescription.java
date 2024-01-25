/**
 * Post Description DB Model
 */

package com.project.application.models.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "POST_DESCRIPTION")
public class PostDescription {
    //Post Description DB Model Class
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String description;

    //Getter and Setters
}

