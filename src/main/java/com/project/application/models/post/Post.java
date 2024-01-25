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
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostLikes> postLikes;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostDescription> postDescription;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostShortDescription> postShortDescription;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostComments> postComments;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostViews> postViews;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTags> postTags;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<UserViews> userViews;

    private String imageUrl;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant updatedOn;

    //Getter and Setters
}
