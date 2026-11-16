package com.placementtracker.common.controller;

import com.placementtracker.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Health and status controller to verify application readiness.
 */
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @Value("${spring.application.name:internship-placement-tracker}")
    private String applicationName = "internship-placement-tracker";

    @Value("${spring.profiles.active:dev}")
    private String activeProfile = "dev";

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getHealthStatus() {
        Map<String, Object> statusData = Map.of(
                "application", applicationName,
                "status", "UP",
                "activeProfile", activeProfile,
                "version", "0.0.1-SNAPSHOT",
                "timestamp", Instant.now().toString(),
                "modules", List.of(
                        "auth",
                        "application",
                        "profile",
                        "skills",
                        "interview",
                        "recruiter",
                        "analytics",
                        "common"
                )
        );

        return ResponseEntity.ok(ApiResponse.success("Internship and Placement Application Tracker API is live", statusData));
    }
}
