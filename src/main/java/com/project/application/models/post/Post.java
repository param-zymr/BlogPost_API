/**
 * Post DB Model
 */

package com.project.application.models.post;

import com.project.application.models.user.User;
import com.project.application.models.user.UserViews;
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
@Table(name = "POSTS")
public class Post {
    //Post DB Model Class
    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostLikes> postLikes;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private PostDescription postDescription;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private PostShortDescription postShortDescription;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostComments> postComments;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private PostViews postViews;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private PostTags postTags;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<UserViews> userViews;

    private String imageUrl;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant updatedOn;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE",
            nullable = false)
    private Boolean isDeleted;

    //Getter and Setters
}
