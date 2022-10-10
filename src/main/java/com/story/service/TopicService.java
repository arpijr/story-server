package com.story.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.story.mapper.TopicMapper;
import com.story.dto.TopicDto;
import com.story.exception.StoryException;
import com.story.model.Topic;
import com.story.repository.TopicRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Transactional
    public TopicDto save(TopicDto topicDto) {
        Topic save = topicRepository.save(topicMapper.mapDtoToTopic(topicDto));
        topicDto.setId(save.getId());
        return topicDto;
    }

    @Transactional(readOnly = true)
    public List<TopicDto> getAll() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::mapTopicToDto)
                .collect(toList());
    }

    public TopicDto getTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new StoryException("No topic found with ID - " + id));
        return topicMapper.mapTopicToDto(topic);
    }
}
