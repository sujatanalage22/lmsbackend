package com.cjc.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public User register(User user) {
//        return userRepository.save(user);
//    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public User register(User user) {
        if (user.getRole() == null) {
            user.setRole("STUDENT");
        }
        return userRepository.save(user);
    }
}

