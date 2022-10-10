package com.story.mapper;


import java.util.List;


import com.story.dto.TopicDto;
import com.story.model.Post;
import com.story.model.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    public TopicDto mapTopicToDto(Topic topic){
        TopicDto resultTopicDto = new TopicDto();
        resultTopicDto.setNumberOfPosts(topic.getPosts().size());
        resultTopicDto.setId(topic.getId());
        resultTopicDto.setName(topic.getName());
        resultTopicDto.setDescription(topic.getDescription());
        return resultTopicDto;
    }

    public Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    public Topic mapDtoToTopic(TopicDto topicDto){

        Topic resultTopic = new Topic();
        resultTopic.setId(topicDto.getId());
        resultTopic.setDescription(topicDto.getDescription());
        resultTopic.setName(topicDto.getName());

        return resultTopic;
    }

}
