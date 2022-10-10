package com.story.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.story.model.Post;
import com.story.model.Topic;
import com.story.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTopic(Topic topic);

    List<Post> findByUser(User user);
}
