package com.cjc.springbootapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JsonIgnore   // ✅ VERY IMPORTANT
    private Course course;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	   public String getContent() { return content; }
	    public void setContent(String content) { this.content = content; }
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();
}
