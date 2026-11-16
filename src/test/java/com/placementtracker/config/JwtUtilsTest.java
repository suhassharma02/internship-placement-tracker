package com.placementtracker.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilsTest {

    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 3600000L);
    }

    @Test
    @DisplayName("Should generate and validate a valid JWT token")
    void shouldGenerateAndValidateToken() {
        String username = "student@example.com";
        List<String> roles = List.of("ROLE_STUDENT");

        String token = jwtUtils.generateTokenFromUsername(username, roles);

        assertThat(token).isNotBlank();
        assertThat(jwtUtils.validateToken(token)).isTrue();
        assertThat(jwtUtils.getUsernameFromToken(token)).isEqualTo(username);
    }

    @Test
    @DisplayName("Should reject invalid or malformed token")
    void shouldRejectInvalidToken() {
        assertThat(jwtUtils.validateToken("invalid.token.here")).isFalse();
        assertThat(jwtUtils.validateToken("")).isFalse();
    }
}
