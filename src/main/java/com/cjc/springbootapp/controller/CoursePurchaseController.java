package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.model.CoursePurchase;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.CourseRepository;
import com.cjc.springbootapp.repository.UserRepository;
import com.cjc.springbootapp.service.CoursePurchaseService;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin
public class CoursePurchaseController {

    @Autowired
    private CoursePurchaseService purchaseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public CoursePurchase purchaseCourse(
            @RequestParam Long customerId,
            @RequestParam Long courseId) {

        User customer = userRepository.findById(customerId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        return purchaseService.purchaseCourse(customer, course);
    }

    @GetMapping("/customer/{customerId}")
    public List<CoursePurchase> customerCourses(
            @PathVariable Long customerId) {

        return purchaseService.getCustomerCourses(customerId);
    }

    @GetMapping("/mentor/{mentorId}")
    public List<CoursePurchase> mentorSales(
            @PathVariable Long mentorId) {

        return purchaseService.getMentorSales(mentorId);
    }
}
