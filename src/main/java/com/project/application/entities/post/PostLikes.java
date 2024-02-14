/**
 * Post Likes DB Model
 */

package com.project.application.entities.post;

import com.project.application.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST_LIKES")
public class PostLikes {
    //Post Likes DB Model Class
    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "is_liked", columnDefinition = "BOOLEAN DEFAULT FALSE",
            nullable = false)
    private Boolean isLiked;

    //Getter and Setters
}

