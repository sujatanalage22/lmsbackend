package com.cjc.springbootapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.DTO.ChapterDTO;
import com.cjc.springbootapp.DTO.CourseDTO;
import com.cjc.springbootapp.DTO.TopicDTO;
import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.service.CourseService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // ADD COURSE
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // ✅ GET ALL COURSES (FOR HOME PAGE)
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses().stream().map(course ->
                new CourseDTO(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getPrice(),
                        course.getMentor(),
                        course.getCategory(),
                        null   // ❗ NO chapters for home page
                )
        ).collect(Collectors.toList());
    }

    // ✅ COURSE DETAILS PAGE
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseWithChapters(@PathVariable Long id) {

        Optional<Course> courseOpt = courseService.getCourseById(id);
        if (courseOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Course course = courseOpt.get();

        List<ChapterDTO> chaptersDTO = course.getChapters().stream().map(ch -> {
            List<TopicDTO> topicsDTO = ch.getTopics().stream()
                    .map(tp -> new TopicDTO(tp.getId(), tp.getTitle(), tp.getContent()))
                    .collect(Collectors.toList());

            return new ChapterDTO(ch.getId(), ch.getTitle(), topicsDTO);
        }).collect(Collectors.toList());

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
