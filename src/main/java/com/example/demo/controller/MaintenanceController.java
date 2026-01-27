package com.example.demo.controller;

import com.example.demo.service.MaintenanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/maintenance")
public class MaintenanceController {
    private final MaintenanceService ms;

    public MaintenanceController(MaintenanceService ms) {
        this.ms = ms;
    }

    @GetMapping("/status")
    public Map<String, Object> status() {
        return Map.of("enabled", ms.isOn());
    }
}
