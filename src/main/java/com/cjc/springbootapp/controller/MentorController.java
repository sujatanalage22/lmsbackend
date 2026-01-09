package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.model.Chapter;
import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.model.CoursePurchase;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.ChapterRepository;
import com.cjc.springbootapp.repository.UserRepository;
import com.cjc.springbootapp.service.CourseService;

@RestController
@RequestMapping("/api/mentor")
@CrossOrigin(origins = "http://localhost:3000")
public class MentorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private CourseService courseService;

    // ✅ 1️⃣ Get all mentors
    @GetMapping("/all")
    public List<User> getAllMentors() {
        return userRepository.findAll()
                .stream()
                .filter(u -> "MENTOR".equals(u.getRole()))
                .toList();
    }

    // ✅ 2️⃣ Get all courses of a mentor
    @GetMapping("/{mentorId}/courses")
    public List<Course> getMentorCourses(@PathVariable Long mentorId) {
        return courseService.getMentorCourses(mentorId);
    }

    // ✅ 3️⃣ Add a new course
    @PostMapping("/course")
    public Course addCourse(@RequestBody Course course) {
        course.setMentor(userRepository.findById(course.getMentor().getId()).orElse(null));
        return courseService.addCourse(course);
    }

    // ✅ 4️⃣ Update a course
    @PutMapping("/course/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course course = courseService.getCourseById(courseId).orElseThrow();
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        return courseService.addCourse(course);
    }

    // ✅ 5️⃣ Delete a course safely
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cannot delete course: " + e.getMessage());
        }
    }

    // ✅ 6️⃣ Chapter endpoints
    @GetMapping("/course/{courseId}/chapters")
    public List<Chapter> getChapters(@PathVariable Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }

    @PostMapping("/course/{courseId}/chapter")
    public Chapter addChapter(@PathVariable Long courseId, @RequestBody Chapter chapter) {
        Course course = courseService.getCourseById(courseId).orElseThrow();
        chapter.setCourse(course);
        return chapterRepository.save(chapter);
    }

    @PutMapping("/chapter/{chapterId}")
    public Chapter updateChapter(@PathVariable Long chapterId, @RequestBody Chapter updated) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow();
        chapter.setTitle(updated.getTitle());
        chapter.setContent(updated.getContent());
        return chapterRepository.save(chapter);
    }

    @DeleteMapping("/chapter/{chapterId}")
    public String deleteChapter(@PathVariable Long chapterId) {
        chapterRepository.deleteById(chapterId);
        return "Deleted";
    }
}
