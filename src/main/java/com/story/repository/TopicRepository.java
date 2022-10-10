package com.story.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.story.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String topicName);
}
