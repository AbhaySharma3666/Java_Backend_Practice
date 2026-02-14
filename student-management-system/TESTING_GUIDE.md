# Testing Guide

## Overview
This guide covers testing strategies and procedures for the Student Management System.

## Test Environment Setup

### Prerequisites
- JDK 17
- Maven 3.6+
- MySQL 8.0+ (for integration tests)
- Postman or cURL (for API testing)

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=StudentServiceTest

# Run tests with coverage
mvn clean test jacoco:report

# Skip tests during build
mvn clean install -DskipTests
```

## Unit Testing

### Service Layer Tests
Test business logic in isolation using mocks.

Example test structure:
```java
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    
    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentServiceImpl studentService;
    
    @Test
    void testGetAllStudents() {
        // Arrange
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);
        
        // Act
        List<Student> result = studentService.getAllStudents();
        
        // Assert
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }
}
```

### Repository Layer Tests
Test data access layer with @DataJpaTest.

```java
@DataJpaTest
class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setName("Test Student");
        student.setEmail("test@college.edu");
        
        Student saved = studentRepository.save(student);
        
        assertNotNull(saved.getId());
        assertEquals("Test Student", saved.getName());
    }
}
```

## Integration Testing

### Controller Tests
Test REST endpoints with @WebMvcTest.

```java
@WebMvcTest(StudentRestController.class)
class StudentRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentService studentService;
    
    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student());
        when(studentService.getAllStudents()).thenReturn(students);
        
        mockMvc.perform(get("/api/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }
}
```

### Full Integration Tests
Test complete application flow with @SpringBootTest.

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentManagementSystemIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Integration Test");
        student.setEmail("integration@college.edu");
        
        ResponseEntity<Student> response = restTemplate.postForEntity(
            "/api/students", student, Student.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }
}
```

## Security Testing

### Authentication Tests
```java
@Test
@WithMockUser(roles = "ADMIN")
void testAdminAccess() throws Exception {
    mockMvc.perform(get("/admin/dashboard"))
        .andExpect(status().isOk());
}

@Test
void testUnauthorizedAccess() throws Exception {
    mockMvc.perform(get("/admin/dashboard"))
        .andExpect(status().isUnauthorized());
}
```

## Manual Testing

### 1. Login Testing
**Test Case**: Admin Login
- Navigate to `http://localhost:8080/login`
- Enter username: `admin`
- Enter password: `admin123`
- Expected: Redirect to admin dashboard

### 2. Student CRUD Testing

**Create Student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "rollNo": "TEST001",
    "name": "Test Student",
    "email": "test@college.edu",
    "phone": "1234567890",
    "gender": "Male",
    "department": {"id": 1}
  }'
```

**Read Students:**
```bash
curl -X GET http://localhost:8080/api/students
```

**Update Student:**
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "rollNo": "TEST001",
    "name": "Updated Name",
    "email": "test@college.edu",
    "phone": "9999999999",
    "gender": "Male",
    "department": {"id": 1}
  }'
```

**Delete Student:**
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

### 3. Role-Based Access Testing

**Admin Access:**
- Login as admin
- Access `/admin/dashboard` - Should succeed
- Access `/admin/students` - Should succeed

**Faculty Access:**
- Login as faculty
- Access `/faculty/dashboard` - Should succeed
- Access `/admin/dashboard` - Should fail (403)

**Student Access:**
- Login as student
- Access `/student/dashboard` - Should succeed
- Access `/admin/dashboard` - Should fail (403)

## Performance Testing

### Load Testing with Apache JMeter
1. Install JMeter
2. Create test plan for API endpoints
3. Configure thread groups (users)
4. Run tests and analyze results

### Database Performance
```sql
-- Check query performance
EXPLAIN SELECT * FROM students WHERE email = 'test@college.edu';

-- Check index usage
SHOW INDEX FROM students;
```

## Test Coverage

### Generate Coverage Report
```bash
mvn clean test jacoco:report
```

View report at: `target/site/jacoco/index.html`

### Coverage Goals
- Line Coverage: > 80%
- Branch Coverage: > 70%
- Method Coverage: > 85%

## Continuous Integration Testing

### GitHub Actions Example
```yaml
name: CI

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run tests
        run: mvn clean test
```

## Test Data Management

### Test Database Setup
```sql
-- Create test database
CREATE DATABASE college_test;

-- Use test profile
spring.profiles.active=test
```

### Test Data Cleanup
```java
@AfterEach
void cleanup() {
    studentRepository.deleteAll();
}
```

## Common Test Scenarios

### 1. Validation Testing
- Test empty fields
- Test invalid email format
- Test duplicate entries
- Test null values

### 2. Error Handling Testing
- Test 404 Not Found
- Test 400 Bad Request
- Test 500 Internal Server Error
- Test custom exceptions

### 3. Boundary Testing
- Test minimum values
- Test maximum values
- Test edge cases

## Best Practices

1. **Arrange-Act-Assert Pattern**: Structure tests clearly
2. **Test Isolation**: Each test should be independent
3. **Meaningful Names**: Use descriptive test method names
4. **Mock External Dependencies**: Isolate unit tests
5. **Clean Test Data**: Reset state after each test
6. **Test Edge Cases**: Cover boundary conditions
7. **Continuous Testing**: Run tests frequently
8. **Code Coverage**: Aim for high coverage
9. **Performance Tests**: Include load testing
10. **Security Tests**: Test authentication and authorization

## Troubleshooting

### Tests Failing
- Check database connection
- Verify test data setup
- Check for port conflicts
- Review error logs

### Slow Tests
- Use @DataJpaTest instead of @SpringBootTest
- Mock external services
- Optimize database queries
- Use in-memory database for tests

## Test Checklist

- [ ] Unit tests for all services
- [ ] Repository tests for data access
- [ ] Controller tests for endpoints
- [ ] Integration tests for workflows
- [ ] Security tests for authentication
- [ ] Validation tests for input
- [ ] Error handling tests
- [ ] Performance tests
- [ ] Load tests
- [ ] Security vulnerability tests

## Resources

- [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [AssertJ Documentation](https://assertj.github.io/doc/)
