package com.story.service;


import static com.story.model.VoteType.UPVOTE;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.story.dto.VoteDto;
import com.story.exception.PostNotFoundException;
import com.story.exception.StoryException;
import com.story.model.Post;
import com.story.model.Vote;
import com.story.repository.PostRepository;
import com.story.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        //post.setVoteCount(0);
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new StoryException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {

            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
