package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.DTO.ChapterDTO;
import com.cjc.springbootapp.DTO.CourseDTO;
import com.cjc.springbootapp.DTO.TopicDTO;
import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.repository.CourseRepository;
import com.cjc.springbootapp.service.CourseService;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    // ===================== ADD COURSE =====================
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // ===================== UPDATE COURSE (MENTOR) =====================
    @PutMapping("/mentor/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course updatedCourse) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());

        return courseRepository.save(course);
    }

    // ===================== GET COURSE FOR EDIT PAGE =====================
    @GetMapping("/mentor/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    // ===================== GET COURSES BY MENTOR =====================
    // API remains SAME → /api/course/mentor/mentor/{mentorId}
    @GetMapping("/mentor/mentor/{mentorId}")
    public List<Course> getCoursesByMentor(@PathVariable Long mentorId) {
        return courseRepository.findByMentorId(mentorId);
    }

    // ===================== HOME PAGE COURSES (NO CHAPTERS) =====================
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getPrice(),
                        course.getMentor(),
                        course.getCategory(),
                        null // ❌ No chapters for home page
                ))
                .toList();
    }

    // ===================== COURSE DETAILS (WITH CHAPTERS + TOPICS) =====================
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseWithChapters(@PathVariable Long id) {

        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<ChapterDTO> chaptersDTO = course.getChapters()
                .stream()
                .map(ch -> new ChapterDTO(
                        ch.getId(),
                        ch.getTitle(),
                        ch.getTopics()
                                .stream()
                                .map(tp -> new TopicDTO(
                                        tp.getId(),
                                        tp.getTitle(),
                                        tp.getContent()
                                ))
                                .toList()
                ))
                .toList();

        CourseDTO courseDTO = new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getMentor(),
                course.getCategory(),
                chaptersDTO
        );

        return ResponseEntity.ok(courseDTO);
    }
}
