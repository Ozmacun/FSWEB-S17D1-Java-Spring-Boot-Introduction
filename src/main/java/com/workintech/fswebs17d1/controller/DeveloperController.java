package com.workintech.fswebs17d1.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    @GetMapping("/course")
    public String getCourseName() {
        return courseName;
    }

    @GetMapping("/name")
    public String getDeveloperName() {
        return developerName;
    }
}
