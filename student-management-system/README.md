# College Student Management System

A production-ready, secure, and scalable Student Management System built with Java Spring Boot following clean architecture and industry best practices.

## 🚀 Features

### Core Functionality
- **Student Management**: CRUD operations for student records
- **Department Management**: Organize students by departments
- **Course Management**: Manage courses and curriculum
- **Attendance Tracking**: Record and monitor student attendance
- **Marks Management**: Track and manage student grades
- **Faculty Management**: Manage faculty information

### Security Features
- Spring Security with role-based access control (RBAC)
- BCrypt password encryption
- JWT token support (infrastructure ready)
- CSRF protection
- Secure authentication and authorization

### Architecture
- Clean Architecture principles
- Layered architecture (Controller → Service → Repository)
- RESTful API endpoints
- MVC pattern for web interface
- Exception handling with global error handler

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.x
- **Security**: Spring Security 6
- **Database**: MySQL 8.x
- **ORM**: Spring Data JPA / Hibernate
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Password Encoding**: BCrypt
- **JWT**: JSON Web Tokens (jjwt 0.11.5)

## 📋 Prerequisites

- JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd student-management-system
```

### 2. Configure Database
Create a MySQL database:
```sql
CREATE DATABASE college;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/college
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🔐 Default Credentials

**Admin User:**
- Username: `admin`
- Password: `admin123`

## 📁 Project Structure

```
src/main/java/com/college/sms/
├── config/              # Configuration classes
│   ├── AppConfig.java
│   └── DataInitializer.java
├── controller/          # Controllers
│   ├── admin/          # Admin controllers
│   ├── api/            # REST API controllers
│   ├── faculty/        # Faculty controllers
│   ├── student/        # Student controllers
│   └── AuthController.java
├── dto/                # Data Transfer Objects
├── entity/             # JPA Entities
│   ├── User.java
│   ├── Role.java
│   ├── Student.java
│   ├── Faculty.java
│   ├── Department.java
│   ├── Course.java
│   ├── Attendance.java
│   └── Marks.java
├── exception/          # Exception handling
├── repository/         # JPA Repositories
├── security/           # Security configuration
│   ├── SecurityConfig.java
│   ├── CustomUserDetailsService.java
│   ├── JwtUtil.java
│   └── JwtFilter.java
├── service/            # Business logic
│   └── impl/          # Service implementations
└── utils/             # Utility classes
```

## 🌐 API Endpoints

### Authentication
- `GET /login` - Login page
- `POST /login` - Authenticate user
- `POST /logout` - Logout user

### Admin Endpoints (Role: ADMIN)
- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/students` - List all students
- `POST /admin/students/save` - Create/Update student
- `GET /admin/students/edit/{id}` - Edit student
- `GET /admin/students/delete/{id}` - Delete student
- `GET /admin/courses` - Manage courses
- `GET /admin/departments` - Manage departments

### Faculty Endpoints (Role: FACULTY)
- `GET /faculty/dashboard` - Faculty dashboard
- `GET /faculty/attendance` - Attendance form
- `POST /faculty/attendance/save` - Save attendance
- `GET /faculty/marks` - Marks form
- `POST /faculty/marks/save` - Save marks

### Student Endpoints (Role: STUDENT)
- `GET /student/dashboard` - Student dashboard
- `GET /student/attendance` - View attendance
- `GET /student/marks` - View marks

### REST API Endpoints
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student

## 🗄️ Database Schema

### Users Table
- id (PK)
- username
- password (encrypted)
- enabled

### Roles Table
- id (PK)
- name (ADMIN, FACULTY, STUDENT)

### Students Table
- id (PK)
- roll_no
- name
- email
- phone
- gender
- department_id (FK)

### Departments Table
- id (PK)
- name

### Courses Table
- id (PK)
- name
- duration
- department_id (FK)

### Faculty Table
- id (PK)
- name
- email
- phone
- subject
- department_id (FK)

### Attendance Table
- id (PK)
- date
- status
- student_id (FK)

### Marks Table
- id (PK)
- subject
- marks
- student_id (FK)

## 🔒 Security Configuration

### Role-Based Access Control
- **ADMIN**: Full access to all resources
- **FACULTY**: Access to attendance and marks management
- **STUDENT**: Read-only access to personal data

### Password Encryption
All passwords are encrypted using BCrypt with strength 10.

### CSRF Protection
CSRF is disabled for API endpoints but enabled for web forms.

## 🧪 Testing

Run tests:
```bash
mvn test
```

## 📦 Building for Production

Create production build:
```bash
mvn clean package -DskipTests
```

The JAR file will be created in `target/` directory.

Run the JAR:
```bash
java -jar target/student-management-system-0.0.1-SNAPSHOT.jar
```

## 🚀 Deployment

### Docker Deployment (Optional)
Create `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t sms-app .
docker run -p 8080:8080 sms-app
```

## 📝 Configuration Properties

Key application properties:
```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/college
spring.datasource.username=root
spring.datasource.password=mysql

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Thymeleaf
spring.thymeleaf.cache=false
```

## 🔧 Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check database credentials
- Ensure database exists

### Port Already in Use
Change port in `application.properties`:
```properties
server.port=8081
```

### Build Failures
- Ensure Java 17 is installed
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn clean install -U`

## 📚 Best Practices Implemented

1. **Clean Architecture**: Separation of concerns with layered architecture
2. **SOLID Principles**: Single responsibility, dependency injection
3. **Security**: Password encryption, role-based access control
4. **Exception Handling**: Global exception handler
5. **Code Organization**: Package by feature
6. **Dependency Injection**: Constructor-based injection
7. **RESTful API**: Standard HTTP methods and status codes
8. **Data Validation**: Input validation at controller level
9. **Logging**: Structured logging (can be enhanced)
10. **Configuration Management**: Externalized configuration

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👥 Authors

- Senior Java Full Stack Developer

## 🙏 Acknowledgments

- Spring Boot Documentation
- Spring Security Reference
- Clean Architecture by Robert C. Martin

## 📞 Support

For support, email support@example.com or create an issue in the repository.

---

**Note**: This is a production-ready template. Customize according to your specific requirements.
