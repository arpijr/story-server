package com.story.mapper;

import com.story.dto.CommentsDto;
import com.story.model.Comment;
import com.story.model.Post;
import com.story.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CommentMapper {
    public Comment map(CommentsDto commentsDto, Post post, User user){

        Comment resultComment = new Comment();
        resultComment.setText(commentsDto.getText());
        resultComment.setCreatedDate(Instant.now());
        resultComment.setPost(post);
        resultComment.setUser(user);
        resultComment.setId(commentsDto.getId());
        return resultComment;
    }

    public CommentsDto mapToDto(Comment comment){

        CommentsDto resultCommentsDto = new CommentsDto();
        resultCommentsDto.setPostId(comment.getPost().getPostId());
        resultCommentsDto.setUserName(comment.getUser().getUsername());
        resultCommentsDto.setText(comment.getText());
        resultCommentsDto.setId(comment.getId());
        resultCommentsDto.setCreatedDate(comment.getCreatedDate());

        return resultCommentsDto;

    }
}