package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Hello", description = "Test endpoints")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @Operation(summary = "Hello public endpoint")
    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
