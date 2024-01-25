/**
 * Post Tags DB Model
 */

package com.project.application.models.post;

import com.project.application.models.tags.Tags;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "POST_TAGS")
public class PostTags {
    //Post Tags DB Model Class
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tag;

    //Getter and Setters
}
