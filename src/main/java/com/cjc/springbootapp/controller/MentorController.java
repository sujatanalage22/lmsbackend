package com.cjc.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.UserRepository;

@RestController
@RequestMapping("/api/mentor")
@CrossOrigin
public class MentorController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllMentors() {
        return userRepository.findAll()
                .stream()
                .filter(u -> "MENTOR".equals(u.getRole()))
                .toList();
    }
}
