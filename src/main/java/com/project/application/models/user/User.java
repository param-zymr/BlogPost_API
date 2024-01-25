/**
 * User DB Model
 */

package com.project.application.models.user;

import com.project.application.models.post.Post;
import com.project.application.models.post.PostComments;
import com.project.application.models.post.PostLikes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    //User DB Model Class
    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    private String passwordHash;

    private String profilePhoto;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant updatedOn;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoles userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostLikes> postLikes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostComments> postComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserViews> userViews;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE",
            nullable = false)
    private Boolean isDeleted;

    //Constructors, Getter and Setters
}
