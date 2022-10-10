package com.story.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.story.mapper.PostMapper;
import com.story.dto.PostRequest;
import com.story.dto.PostResponse;
import com.story.exception.PostNotFoundException;
import com.story.exception.TopicNotFoundException;
import com.story.model.Post;
import com.story.model.Topic;
import com.story.model.User;
import com.story.repository.PostRepository;
import com.story.repository.TopicRepository;
import com.story.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Topic topic = topicRepository.findByName(postRequest.getTopicName())
                .orElseThrow(() -> new TopicNotFoundException(postRequest.getTopicName()));
        postRepository.save(postMapper.map(postRequest, topic, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException(topicId.toString()));
        List<Post> posts = postRepository.findAllByTopic(topic);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
