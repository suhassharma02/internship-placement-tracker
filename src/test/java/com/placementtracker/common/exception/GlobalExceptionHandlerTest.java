package com.placementtracker.common.exception;

import com.placementtracker.common.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Should handle ResourceNotFoundException with 404 NOT_FOUND")
    void shouldHandleResourceNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Student", "id", 101L);
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleResourceNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).contains("Student not found with id: '101'");
    }

    @Test
    @DisplayName("Should handle BadRequestException with 400 BAD_REQUEST")
    void shouldHandleBadRequest() {
        BadRequestException ex = new BadRequestException("Invalid input data");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleBadRequest(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid input data");
    }

    @Test
    @DisplayName("Should handle ConflictException with 409 CONFLICT")
    void shouldHandleConflict() {
        ConflictException ex = new ConflictException("Email already exists");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleConflict(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).isEqualTo("Email already exists");
    }

    @Test
    @DisplayName("Should handle UnauthorizedException with 401 UNAUTHORIZED")
    void shouldHandleUnauthorized() {
        UnauthorizedException ex = new UnauthorizedException("Invalid token");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleUnauthorized(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid token");
    }

    @Test
    @DisplayName("Should handle AccessDeniedException with 403 FORBIDDEN")
    void shouldHandleAccessDenied() {
        AccessDeniedException ex = new AccessDeniedException("Forbidden");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleAccessDenied(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).contains("Access denied");
    }

    @Test
    @DisplayName("Should handle generic Exception with 500 INTERNAL_SERVER_ERROR")
    void shouldHandleGenericException() {
        RuntimeException ex = new RuntimeException("Unexpected database failure");
        ResponseEntity<ApiResponse<Void>> response = exceptionHandler.handleGenericException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).contains("An unexpected error occurred");
    }
}
