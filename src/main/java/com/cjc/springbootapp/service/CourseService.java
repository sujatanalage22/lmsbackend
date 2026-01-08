package com.cjc.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll(); // ✅ returns objects
    }

//    public List<Course> getAllCourses() {
//        return courseRepository.findAll();
//    }

    public List<Course> getMentorCourses(Long mentorId) {
        return courseRepository.findByMentorId(mentorId);
    }
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

}

