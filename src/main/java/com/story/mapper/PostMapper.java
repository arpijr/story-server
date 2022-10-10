package com.story.mapper;

import static com.story.model.VoteType.DOWNVOTE;
import static com.story.model.VoteType.UPVOTE;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.story.dto.PostRequest;
import com.story.dto.PostResponse;
import com.story.model.Post;
import com.story.model.Topic;
import com.story.model.User;
import com.story.model.Vote;
import com.story.model.VoteType;
import com.story.repository.CommentRepository;
import com.story.repository.VoteRepository;
import com.story.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    public Post map(PostRequest postRequest, Topic topic, User user){

        Post post = new Post();
        post.setCreatedDate(java.time.Instant.now());
        post.setDescription(postRequest.getDescription());
        post.setTopic(topic);
        post.setUser(user);

        return post;

    }

    public PostResponse mapToDto(Post post){

        PostResponse resultPostResponse = new PostResponse();
        resultPostResponse.setId(post.getPostId());
        resultPostResponse.setTopicName(post.getTopic().getName());
        resultPostResponse.setUserName(post.getUser().getUsername());
        resultPostResponse.setCommentCount(commentCount(post));
        resultPostResponse.setDuration("about a minute ago");
        resultPostResponse.setUpVote(isPostUpVoted(post));
        resultPostResponse.setDownVote(isPostDownVoted(post));
        resultPostResponse.setDescription(post.getDescription());
        resultPostResponse.setUrl(post.getUrl());
        return resultPostResponse;
    }

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return Instant.now().toString();
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}