package com.cjc.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjc.springbootapp.model.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByCourseId(Long courseId);
}

