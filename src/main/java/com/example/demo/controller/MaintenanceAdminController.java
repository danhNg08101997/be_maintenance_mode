package com.example.demo.controller;

import com.example.demo.service.MaintenanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Maintenance", description = "Test Maintenance")
@RestController
@RequestMapping("/api/v1/admin/maintenance")
public class MaintenanceAdminController {

    private final MaintenanceService ms;

    public MaintenanceAdminController(MaintenanceService ms) {
        this.ms = ms;
    }

    @PostMapping("/enable")
    public void enable(Authentication auth) {
        ms.set(true, auth.getName());
    }

    @PostMapping("/disable")
    public void disable(Authentication auth) {
        ms.set(false, auth.getName());
    }
}
