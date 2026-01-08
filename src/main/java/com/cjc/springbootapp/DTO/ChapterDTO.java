package com.cjc.springbootapp.DTO;

import java.util.List;



import java.util.List;

public class ChapterDTO {
    private Long id;
    private String title;
    private List<TopicDTO> topics;

    public ChapterDTO(Long id, String title, List<TopicDTO> topics) {
        this.id = id;
        this.title = title;
        this.topics = topics;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public List<TopicDTO> getTopics() { return topics; }
}


