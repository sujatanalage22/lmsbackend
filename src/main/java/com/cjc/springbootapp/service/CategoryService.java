package com.cjc.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.CourseCategory;
import com.cjc.springbootapp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CourseCategory addCategory(CourseCategory category) {
        return categoryRepository.save(category);
    }

    public List<CourseCategory> getAll() {
        return categoryRepository.findAll();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
