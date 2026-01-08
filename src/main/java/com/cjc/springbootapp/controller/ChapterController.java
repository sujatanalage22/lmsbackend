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

import com.cjc.springbootapp.model.Chapter;
import com.cjc.springbootapp.service.ChapterService;

@RestController
@RequestMapping("/api/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping
    public Chapter addChapter(@RequestBody Chapter chapter) {
        return chapterService.addChapter(chapter);
    }

    @GetMapping("/course/{courseId}")
    public List<Chapter> getChapters(@PathVariable Long courseId) {
        return chapterService.getChaptersByCourse(courseId);
    }
}

