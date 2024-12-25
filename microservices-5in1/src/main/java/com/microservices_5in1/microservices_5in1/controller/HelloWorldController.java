package com.microservices_5in1.microservices_5in1.controller;

import com.microservices_5in1.microservices_5in1.dto.user.User;
import com.microservices_5in1.microservices_5in1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
//REST API
@RestController
@RequestMapping("/test")
public class HelloWorldController {

    private final UserService userService;

    // /hello world
    @GetMapping(value = "/hello-world")
    public String helloWorld() {
        return "Hello, World!";
    }

    //GET ALL USERS
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveAUser(@PathVariable("id") int id) {
        return userService.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> retrieveAUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public String deleteAUser(@PathVariable("id") int id) {
        return userService.deleteAUser(id);
    }
}
