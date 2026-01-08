package com.cjc.springbootapp.DTO;

public class TopicDTO {
    private Long id;
    private String title;
    private String content;

    public TopicDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
