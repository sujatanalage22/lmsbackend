package com.cjc.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://fantastic-croissant-1d6c3f.netlify.app"
	})

public class AuthController {

    @Autowired
    private UserService userService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedUser == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", loggedUser.getId());
        response.put("name", loggedUser.getName());
        response.put("email", loggedUser.getEmail());
        response.put("role", loggedUser.getRole());

        return ResponseEntity.ok(response);
    }

    // REGISTER (Customer / Mentor)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
