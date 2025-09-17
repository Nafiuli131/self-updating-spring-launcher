package com.example.self_updating_spring_launcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @GetMapping
    public String helloController(){
        return "Hello Nafiul";
    }
    
}
