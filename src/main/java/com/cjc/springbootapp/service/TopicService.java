package com.cjc.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Topic;
import com.cjc.springbootapp.repository.TopicRepository;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> getTopics(Long chapterId) {
        return topicRepository.findByChapterId(chapterId);
    }
}

