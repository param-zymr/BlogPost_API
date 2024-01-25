/**
 * Post Likes DB Model
 */

package com.project.application.models.post;

import com.project.application.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "POST_LIKES")
public class PostLikes {
    //Post Likes DB Model Class
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "is_liked", columnDefinition = "BOOLEAN DEFAULT FALSE",
            nullable = false)
    private Boolean isLiked;

    //Getter and Setters
}

