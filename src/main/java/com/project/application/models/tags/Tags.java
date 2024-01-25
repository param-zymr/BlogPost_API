/**
 * Tags DB Model
 */

package com.project.application.models.tags;

import com.project.application.models.post.PostTags;
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
@Table(name = "TAGS")
public class Tags {
    //Tags DB Model Class
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<PostTags> postTags;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    private Instant updatedOn;

    //Getter and Setters
}
