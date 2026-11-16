package com.placementtracker.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    @DisplayName("Should create successful response with default message")
    void shouldCreateSuccessResponseWithData() {
        String payload = "Sample Data";
        ApiResponse<String> response = ApiResponse.success(payload);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Operation successful");
        assertThat(response.getData()).isEqualTo(payload);
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @DisplayName("Should create successful response with custom message and data")
    void shouldCreateSuccessResponseWithCustomMessage() {
        Integer payload = 42;
        ApiResponse<Integer> response = ApiResponse.success("Custom Success", payload);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Custom Success");
        assertThat(response.getData()).isEqualTo(42);
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @DisplayName("Should create error response with null data")
    void shouldCreateErrorResponse() {
        ApiResponse<Void> response = ApiResponse.error("Something went wrong");

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Something went wrong");
        assertThat(response.getData()).isNull();
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @DisplayName("Should create error response with error payload")
    void shouldCreateErrorResponseWithPayload() {
        String errorDetail = "Field validation failure";
        ApiResponse<String> response = ApiResponse.error("Error occurred", errorDetail);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Error occurred");
        assertThat(response.getData()).isEqualTo(errorDetail);
    }
}
