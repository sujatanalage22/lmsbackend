package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.springbootapp.model.Topic;
import com.cjc.springbootapp.service.TopicService;

@RestController
@RequestMapping("/api/topic")
@CrossOrigin
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public Topic addTopic(@RequestBody Topic topic) {
        return topicService.addTopic(topic);
    }

    @GetMapping("/chapter/{chapterId}")
    public List<Topic> getTopics(@PathVariable Long chapterId) {
        return topicService.getTopics(chapterId);
    }
}
