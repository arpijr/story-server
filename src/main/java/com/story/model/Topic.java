package com.story.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(fetch = EAGER)
    private List<Post> posts;
    private Instant createdDate;
    @ManyToOne(fetch = EAGER)
    private User user;
}
