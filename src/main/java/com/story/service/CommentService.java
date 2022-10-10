package com.story.service;


import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.story.dto.CommentsDto;
import com.story.exception.PostNotFoundException;
import com.story.mapper.CommentMapper;
import com.story.model.Comment;
import com.story.model.Post;
import com.story.model.User;
import com.story.repository.CommentRepository;
import com.story.repository.PostRepository;
import com.story.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        System.out.println("SAVING COMMENT" + commentsDto.getUserName() + " ID: " + commentsDto.getId() + " POST_ID: " + commentsDto.getPostId() );
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

    }


    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }


    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

}
