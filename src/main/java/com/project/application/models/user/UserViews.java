/**
 * User Views DB Model
 */

package com.project.application.models.user;

import com.project.application.models.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_VIEWS")
public class UserViews {
    //User Views DB Model Class
    @Id
    @Column(nullable = false, unique=true)
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

    //Getter and Setters
}
