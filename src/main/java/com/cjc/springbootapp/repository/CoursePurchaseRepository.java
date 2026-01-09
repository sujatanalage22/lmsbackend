package com.cjc.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjc.springbootapp.model.CoursePurchase;

import jakarta.transaction.Transactional;

public interface CoursePurchaseRepository
extends JpaRepository<CoursePurchase, Long> {

List<CoursePurchase> findByCustomerId(Long customerId);

List<CoursePurchase> findByCourseMentorId(Long mentorId);
@Transactional
void deleteByCourseId(Long courseId);

boolean existsByCustomerIdAndCourseId(Long customerId, Long courseId);
}

