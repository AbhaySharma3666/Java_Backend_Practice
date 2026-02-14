# API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication
The API uses Spring Security with form-based authentication and role-based access control.

### Login
```http
POST /login
Content-Type: application/x-www-form-urlencoded

username=admin&password=admin123
```

## REST API Endpoints

### Student Management API

#### 1. Get All Students
```http
GET /api/students
```

**Response:**
```json
[
  {
    "id": 1,
    "rollNo": "CS202401001",
    "name": "John Doe",
    "email": "john.doe@college.edu",
    "phone": "1234567890",
    "gender": "Male",
    "department": {
      "id": 1,
      "name": "Computer Science"
    }
  }
]
```

#### 2. Get Student by ID
```http
GET /api/students/{id}
```

**Response:**
```json
{
  "id": 1,
  "rollNo": "CS202401001",
  "name": "John Doe",
  "email": "john.doe@college.edu",
  "phone": "1234567890",
  "gender": "Male",
  "department": {
    "id": 1,
    "name": "Computer Science"
  }
}
```

#### 3. Create Student
```http
POST /api/students
Content-Type: application/json

{
  "rollNo": "CS202401003",
  "name": "Alice Johnson",
  "email": "alice.johnson@college.edu",
  "phone": "1234567895",
  "gender": "Female",
  "department": {
    "id": 1
  }
}
```

**Response:**
```json
{
  "id": 6,
  "rollNo": "CS202401003",
  "name": "Alice Johnson",
  "email": "alice.johnson@college.edu",
  "phone": "1234567895",
  "gender": "Female",
  "department": {
    "id": 1,
    "name": "Computer Science"
  }
}
```

#### 4. Update Student
```http
PUT /api/students/{id}
Content-Type: application/json

{
  "rollNo": "CS202401003",
  "name": "Alice Johnson Updated",
  "email": "alice.johnson@college.edu",
  "phone": "9999999999",
  "gender": "Female",
  "department": {
    "id": 1
  }
}
```

**Response:**
```json
{
  "id": 6,
  "rollNo": "CS202401003",
  "name": "Alice Johnson Updated",
  "email": "alice.johnson@college.edu",
  "phone": "9999999999",
  "gender": "Female",
  "department": {
    "id": 1,
    "name": "Computer Science"
  }
}
```

#### 5. Delete Student
```http
DELETE /api/students/{id}
```

**Response:**
```
204 No Content
```

## Web Endpoints

### Authentication Endpoints

#### Login Page
```http
GET /login
```
Returns the login page.

#### Logout
```http
POST /logout
```
Logs out the current user.

### Admin Endpoints (Requires ADMIN role)

#### Admin Dashboard
```http
GET /admin/dashboard
```

#### List All Students
```http
GET /admin/students
```

#### Add Student Form
```http
GET /admin/students/add
```

#### Save Student
```http
POST /admin/students/save
Content-Type: application/x-www-form-urlencoded

rollNo=CS202401004&name=Bob&email=bob@college.edu&phone=1234567896&gender=Male&department.id=1
```

#### Edit Student
```http
GET /admin/students/edit/{id}
```

#### Delete Student
```http
GET /admin/students/delete/{id}
```

#### Manage Courses
```http
GET /admin/courses
```

#### Save Course
```http
POST /admin/courses/save
Content-Type: application/x-www-form-urlencoded

name=B.Tech AI&duration=4&department.id=1
```

#### Manage Departments
```http
GET /admin/departments
```

#### Save Department
```http
POST /admin/departments/save
Content-Type: application/x-www-form-urlencoded

name=Artificial Intelligence
```

### Faculty Endpoints (Requires FACULTY role)

#### Faculty Dashboard
```http
GET /faculty/dashboard
```

#### Attendance Form
```http
GET /faculty/attendance
```

#### Save Attendance
```http
POST /faculty/attendance/save
Content-Type: application/x-www-form-urlencoded

date=2024-01-20&status=Present&student.id=1
```

#### Marks Form
```http
GET /faculty/marks
```

#### Save Marks
```http
POST /faculty/marks/save
Content-Type: application/x-www-form-urlencoded

subject=Mathematics&marks=95&student.id=1
```

### Student Endpoints (Requires STUDENT role)

#### Student Dashboard
```http
GET /student/dashboard
```

#### View Attendance
```http
GET /student/attendance
```

#### View Marks
```http
GET /student/marks
```

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "message": "Invalid input data"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "message": "Authentication required"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "message": "Access denied"
}
```

### 404 Not Found
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "message": "Resource not found"
}
```

### 500 Internal Server Error
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "message": "Internal server error"
}
```

## Testing with cURL

### Get All Students
```bash
curl -X GET http://localhost:8080/api/students
```

### Create Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "rollNo": "CS202401005",
    "name": "Test User",
    "email": "test@college.edu",
    "phone": "1234567897",
    "gender": "Male",
    "department": {"id": 1}
  }'
```

### Update Student
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "rollNo": "CS202401001",
    "name": "John Doe Updated",
    "email": "john.doe@college.edu",
    "phone": "9999999999",
    "gender": "Male",
    "department": {"id": 1}
  }'
```

### Delete Student
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

## Testing with Postman

1. Import the endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. For authenticated endpoints, first login via `/login`
4. Use the session cookie for subsequent requests

## Rate Limiting
Currently, no rate limiting is implemented. Consider adding rate limiting for production use.

## Versioning
API Version: v1 (implicit)
Future versions can be added with `/api/v2/` prefix.

## Notes
- All timestamps are in ISO 8601 format
- All endpoints return JSON except web pages
- CSRF is disabled for API endpoints
- Use proper authentication for production
