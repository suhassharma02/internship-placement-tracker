# Configuration Package Guidelines (`com.placementtracker.config`)

## Purpose
The `config/` package centralizes cross-cutting Spring bean definitions and framework configurations shared across all application modules.

## Key Components
1. `SecurityConfig.java`: Configures the Spring Security filter chain, password encoder (`BCryptPasswordEncoder`), JWT authentication filter, CORS settings, and public vs. authenticated route matchers.
2. `JpaConfig.java`: Enables JPA auditing (`@EnableJpaAuditing`) to activate automatic timestamps on `BaseAuditEntity`.
3. `WebConfig.java`: Configures global CORS mappings, JSON date/time formatters, and request interceptors.

## Guidelines
- Do not define module-specific business beans in this package.
- Ensure all bean definitions are stateless and thread-safe.
- When adding security rules, coordinate with Developer 1 (Auth owner) to avoid regressions in route protections.
