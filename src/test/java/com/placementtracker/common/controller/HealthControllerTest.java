package com.placementtracker.common.controller;

import com.placementtracker.common.response.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HealthControllerTest {

    @Test
    @DisplayName("Health endpoint returns UP status and active modules")
    void shouldReturnHealthStatus() {
        HealthController controller = new HealthController();
        ResponseEntity<ApiResponse<Map<String, Object>>> response = controller.getHealthStatus();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isTrue();
        assertThat(response.getBody().getData()).containsEntry("status", "UP");
        assertThat(response.getBody().getData()).containsKey("modules");
    }
}
