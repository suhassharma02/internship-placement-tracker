package com.placementtracker.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Enables Spring Data JPA auditing for auto-populating @CreatedDate and @LastModifiedDate.
 * Activates automatically whenever an EntityManagerFactory bean is present.
 */
@Configuration
@ConditionalOnBean(EntityManagerFactory.class)
@EnableJpaAuditing
public class JpaConfig {
}
