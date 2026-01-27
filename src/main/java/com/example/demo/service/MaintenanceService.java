package com.example.demo.service;

import com.example.demo.entity.AppConfig;
import com.example.demo.repository.AppConfigRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MaintenanceService {
    private final AppConfigRepository appConfigRepository;

    public MaintenanceService(AppConfigRepository appConfigRepository) {
        this.appConfigRepository = appConfigRepository;
    }

    public boolean isOn(){
        return appConfigRepository.findById("maintenance")
                .map(AppConfig::getValue)
                .orElse(false);
    }

    public void set(boolean enabled, String updatedBy){
        var e = appConfigRepository.findById("maintenance").orElseThrow();
        e.setValue(enabled);
        e.setUpdatedBy(updatedBy);
        e.setUpdatedAt(Instant.now());

        appConfigRepository.save(e);
    }

}
