# REST API Documentation: Internship and Placement Application Tracker

## 1. Unified Response Specifications

All REST endpoints adhere to a standardized JSON response envelope. Controllers must wrap payloads in `ApiResponse<T>` and never return entities or raw untyped maps directly.

### 1.1 Success Response Envelope
```json
{
  "success": true,
  "message": "Operation completed successfully.",
  "data": { ... },
  "timestamp": "2026-11-16T14:40:00Z"
}
```

### 1.2 Paginated Success Response Envelope
```json
{
  "success": true,
  "message": "Items retrieved successfully.",
  "data": {
    "content": [ ... ],
    "pageNumber": 0,
    "pageSize": 20,
    "totalElements": 85,
    "totalPages": 5,
    "last": false
  },
  "timestamp": "2026-11-16T14:40:00Z"
}
```

### 1.3 Error Response Envelope
```json
{
  "success": false,
  "message": "Validation failed: 'email' must be a valid email address.",
  "data": {
    "fieldErrors": {
      "email": "must be a well-formed email address"
    }
  },
  "timestamp": "2026-11-16T14:40:00Z"
}
```

---

## 2. Standard HTTP Status Codes

| HTTP Status Code | Condition |
| :--- | :--- |
| `200 OK` | Successful retrieval, modification, or generic operation. |
| `201 CREATED` | Resource created successfully (e.g. registration, application submission). |
| `204 NO CONTENT` | Successful deletion where no payload is returned. |
| `400 BAD REQUEST` | Invalid argument, schema violation, or failed business validation. |
| `401 UNAUTHORIZED` | Missing, expired, or invalid JWT Bearer token. |
| `403 FORBIDDEN` | Valid token but caller lacks required Role (e.g., student calling admin endpoint). |
| `404 NOT FOUND` | Resource identified by ID or query parameters does not exist. |
| `409 CONFLICT` | Unique constraint violation (e.g. duplicate email, duplicate student registration). |
| `500 INTERNAL SERVER ERROR` | Unhandled runtime exception safely masked by GlobalExceptionHandler. |

---

## 3. Core Module Endpoints

### 3.1 Authentication & Security (`/api/v1/auth`)
- `POST /api/v1/auth/register` - Register a new student or recruiter account.
- `POST /api/v1/auth/login` - Authenticate with email/password and obtain JWT.
- `GET  /api/v1/auth/me` - Retrieve the current authenticated user's profile and roles.

### 3.2 Student Profiles (`/api/v1/profiles`)
- `GET  /api/v1/profiles/me` - Get profile of authenticated student.
- `POST /api/v1/profiles` - Initialize or update student profile.
- `GET  /api/v1/profiles/{id}` - Admin/recruiter view of a specific student.
- `PUT  /api/v1/profiles/resume` - Update resume link or details.

### 3.3 Skill Taxonomy & Matching (`/api/v1/skills`)
- `GET  /api/v1/skills` - List skills taxonomy.
- `POST /api/v1/skills` - Admin: register a new taxonomy skill.
- `POST /api/v1/skills/student` - Attach skills to student profile with proficiency.
- `GET  /api/v1/skills/match/{jobId}` - Match candidate skills against job requirements.

### 3.4 Job Postings & Applications (`/api/v1/jobs`, `/api/v1/applications`)
- `GET  /api/v1/jobs` - Paginated list of active job/internship openings.
- `POST /api/v1/jobs` - Recruiter/Admin: create a new job posting.
- `POST /api/v1/applications` - Student: submit application for a job.
- `GET  /api/v1/applications/my` - Student: view personal application history.
- `PATCH /api/v1/applications/{id}/status` - Recruiter: update status (`SHORTLISTED`, `REJECTED`, etc.).

### 3.5 Interview Scheduling (`/api/v1/interviews`)
- `POST /api/v1/interviews` - Schedule an interview round for an application.
- `GET  /api/v1/interviews/application/{appId}` - Get rounds for an application.
- `PUT  /api/v1/interviews/{id}/feedback` - Record interviewer score and feedback.

### 3.6 Placement Analytics (`/api/v1/analytics`)
- `GET  /api/v1/analytics/dashboard` - High-level metrics: total applied, placed, active recruiters.
- `GET  /api/v1/analytics/department-rates` - Placement percentage by department.
- `GET  /api/v1/analytics/salary-insights` - Average and highest package distribution.
