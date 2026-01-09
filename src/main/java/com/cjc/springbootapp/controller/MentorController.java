package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.model.Chapter;
import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.model.CoursePurchase;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.CourseRepository;
import com.cjc.springbootapp.repository.ChapterRepository;
import com.cjc.springbootapp.repository.CoursePurchaseRepository;
import com.cjc.springbootapp.repository.UserRepository;

@RestController
@RequestMapping("/api/mentor")
@CrossOrigin(origins = "http://localhost:3000")
public class MentorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePurchaseRepository purchaseRepository;

    // 1️⃣ Get all mentors
    @GetMapping("/all")
    public List<User> getAllMentors() {
        return userRepository.findAll()
                .stream()
                .filter(u -> "MENTOR".equals(u.getRole()))
                .toList();
    }

    // 2️⃣ Get all courses of a mentor
    @GetMapping("/{mentorId}/courses")
    public List<Course> getMentorCourses(@PathVariable Long mentorId) {
        return courseRepository.findAll()
                .stream()
                .filter(c -> c.getMentor().getId().equals(mentorId))
                .toList();
    }

    // 3️⃣ Add a new course
    @PostMapping("/course")
    public Course addCourse(@RequestBody Course course) {
        course.setMentor(userRepository.findById(course.getMentor().getId()).orElse(null));
        return courseRepository.save(course);
    }

    // 4️⃣ Update a course
    @PutMapping("/course/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        return courseRepository.save(course);
    }
    @Autowired
    private ChapterRepository chapterRepository;

    // 1️⃣ Get chapters for a course
    @GetMapping("/course/{courseId}/chapters")
    public List<Chapter> getChapters(@PathVariable Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }

    // 2️⃣ Add a chapter to a course
    @PostMapping("/course/{courseId}/chapter")
    public Chapter addChapter(@PathVariable Long courseId, @RequestBody Chapter chapter) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        chapter.setCourse(course);
        return chapterRepository.save(chapter);
    }

    // 3️⃣ Update chapter
    @PutMapping("/chapter/{chapterId}")
    public Chapter updateChapter(@PathVariable Long chapterId, @RequestBody Chapter updated) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow();
        chapter.setTitle(updated.getTitle());
        chapter.setContent(updated.getContent());
        return chapterRepository.save(chapter);
    }

    // 4️⃣ Delete chapter
    @DeleteMapping("/chapter/{chapterId}")
    public String deleteChapter(@PathVariable Long chapterId) {
        chapterRepository.deleteById(chapterId);
        return "Deleted";
    }


    // 5️⃣ Delete a course
    @DeleteMapping("/course/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        courseRepository.deleteById(courseId);
        return "Deleted";
    }

    // 6️⃣ Get purchases for a specific course
    @GetMapping("/course/{courseId}/purchases")
    public List<CoursePurchase> getCoursePurchases(@PathVariable Long courseId) {
        return purchaseRepository.findAll()
                .stream()
                .filter(p -> p.getCourse().getId().equals(courseId))
                .toList();
    }
}
