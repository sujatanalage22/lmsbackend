package com.cjc.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Chapter;
import com.cjc.springbootapp.repository.ChapterRepository;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter addChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public List<Chapter> getChaptersByCourse(Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }
}
