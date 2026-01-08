package com.cjc.springbootapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Course;
import com.cjc.springbootapp.model.CoursePurchase;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.CoursePurchaseRepository;

@Service
public class CoursePurchaseService {

    @Autowired
    private CoursePurchaseRepository purchaseRepository;

    public CoursePurchase purchaseCourse(User customer, Course course) {

        boolean alreadyPurchased =
                purchaseRepository.existsByCustomerIdAndCourseId(
                        customer.getId(), course.getId());

        if (alreadyPurchased) {
            throw new RuntimeException("Course already purchased");
        }

        CoursePurchase purchase = new CoursePurchase();
        purchase.setCustomer(customer);
        purchase.setCourse(course);
        purchase.setPurchaseDate(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    public List<CoursePurchase> getCustomerCourses(Long customerId) {
        return purchaseRepository.findByCustomerId(customerId);
    }

    public List<CoursePurchase> getMentorSales(Long mentorId) {
        return purchaseRepository.findByCourseMentorId(mentorId);
    }
}
