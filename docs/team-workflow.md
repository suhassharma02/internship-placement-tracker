# Team Workflow & Engineering Standards

## 1. Git Branch Strategy
We adhere to a GitFlow-inspired branching strategy to streamline multi-developer collaboration across the 3 ownership streams.

- `main`: Production-ready release branch. Directly deployable.
- `develop`: Primary integration branch. All features merge here first.
- Feature branches:
  - Developer 1: `feat/auth-jwt-impl`, `feat/application-lifecycle`
  - Developer 2: `feat/student-profile`, `feat/skill-matching`
  - Developer 3: `feat/interview-rounds`, `feat/recruiter-analytics`
  - Shared: `feat/shared-foundation`, `fix/db-config`

---

## 2. Conventional Commits Standard
All commit messages must follow the [Conventional Commits](https://www.conventionalcommits.org/) format:

```
<type>(<scope>): <short description>

[optional body]
```

### Types:
- `feat`: A new feature or endpoint.
- `fix`: A bug fix or correction.
- `docs`: Documentation updates only.
- `refactor`: Code change that neither fixes a bug nor adds a feature.
- `test`: Adding or correcting tests.
- `chore`: Build tasks, dependency bumps, or tool configurations.

### Examples:
- `feat(auth): implement jwt token generation and validation filter`
- `feat(profile): create student profile entity and dto mapping`
- `fix(interview): resolve null pointer when meeting link is omitted`
- `test(common): add unit tests for global exception handler`

---

## 3. Code Review & Quality Gates

1. **Build Verification**: Before raising a pull request or merging, developers must run:
   - Windows: `.\mvnw.cmd clean test`
   - Linux/macOS: `./mvnw clean test`
   All unit and integration tests must pass cleanly with 0 failures.
2. **Layering Rule**: Controllers must only inject Services; Services must only inject Repositories and other domain Services. Direct Repository access from Controllers is strictly forbidden.
3. **DTO Separation**: Never return JPA Entities directly to the API consumer. Always project through Request and Response DTOs.
4. **Validation**: All incoming requests that modify state must include `@Valid` and appropriate Jakarta validation constraints (`@NotBlank`, `@NotNull`, `@Min`, `@Email`).
