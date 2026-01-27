package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "app_config", schema = "maintenance_mode")
@Data
@Getter
@Setter
@NoArgsConstructor
public class AppConfig {

    @Id
    @Column(nullable = false, unique = true)
    private String key;

    private Boolean value;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
