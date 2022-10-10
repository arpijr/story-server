package com.story.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    private VoteType voteType;
    @NotNull
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
