package com.cjc.springbootapp.DTO;

import java.util.List;

public class CourseDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Object mentor;
    private Object category;
    private List<ChapterDTO> chapters;

    public CourseDTO(Long id, String title, String description,
                     Double price, Object mentor,
                     Object category, List<ChapterDTO> chapters) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.mentor = mentor;
        this.category = category;
        this.chapters = chapters;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Object getMentor() { return mentor; }
    public Object getCategory() { return category; }
    public List<ChapterDTO> getChapters() { return chapters; }
}
