package com.story.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.story.model.Comment;
import com.story.model.Post;
import com.story.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);

}
