package com.cjc.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.repository.CourseRepository;
import com.cjc.springbootapp.repository.CoursePurchaseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePurchaseRepository purchaseRepo;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getMentorCourses(Long mentorId) {
        return courseRepository.findByMentorId(mentorId);
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        // 1️⃣ Delete all purchases for this course first
        purchaseRepo.deleteByCourseId(courseId);

        // 2️⃣ Then delete the course
        courseRepository.deleteById(courseId);
    }
}
