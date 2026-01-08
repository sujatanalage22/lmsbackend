package com.cjc.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjc.springbootapp.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByMentorId(Long mentorId);
}