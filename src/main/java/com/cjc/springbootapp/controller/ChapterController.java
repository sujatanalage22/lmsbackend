package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.springbootapp.model.Chapter;
import com.cjc.springbootapp.repository.ChapterRepository;
import com.cjc.springbootapp.service.ChapterService;

@RestController
@RequestMapping("/api/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterRepository chapterRepository;

    // ADD CHAPTER
    @PostMapping
    public Chapter addChapter(@RequestBody Chapter chapter) {
        return chapterService.addChapter(chapter);
    }

    // GET CHAPTERS BY COURSE
    @GetMapping("/course/{courseId}")
    public List<Chapter> getChapters(@PathVariable Long courseId) {
        return chapterService.getChaptersByCourse(courseId);
    }

    // UPDATE CHAPTER
    @PutMapping("/{chapterId}")
    public Chapter updateChapter(
            @PathVariable Long chapterId,
            @RequestBody Chapter updated) {

        Chapter ch = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        ch.setTitle(updated.getTitle());
        ch.setContent(updated.getContent());

        return chapterRepository.save(ch);
    }

    // DELETE CHAPTER
    @DeleteMapping("/{chapterId}")
    public void deleteChapter(@PathVariable Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }
}


