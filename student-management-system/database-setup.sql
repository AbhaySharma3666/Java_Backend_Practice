CREATE DATABASE IF NOT EXISTS college;
USE college;

# Role
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL
);

# User
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100),
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

# user_roles
CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(role_id) REFERENCES roles(id)
);

# departments
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

#students
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roll_no VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    dob DATE,
    gender VARCHAR(10),
    email VARCHAR(100),
    phone VARCHAR(15),
    department_id INT,
    user_id INT,
    FOREIGN KEY(department_id) REFERENCES departments(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

#faculty
CREATE TABLE faculty (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    department_id INT,
    user_id INT,
    FOREIGN KEY(department_id) REFERENCES departments(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

#courses
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    duration INT,
    department_id INT,
    FOREIGN KEY(department_id) REFERENCES departments(id)
);

#subjects
CREATE TABLE subjects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    course_id INT,
    faculty_id INT,
    FOREIGN KEY(course_id) REFERENCES courses(id),
    FOREIGN KEY(faculty_id) REFERENCES faculty(id)
);

#enrollments
CREATE TABLE enrollments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    year INT,
    FOREIGN KEY(student_id) REFERENCES students(id),
    FOREIGN KEY(course_id) REFERENCES courses(id)
);

#attendance
CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject_id INT,
    date DATE,
    status VARCHAR(10),
    FOREIGN KEY(student_id) REFERENCES students(id),
    FOREIGN KEY(subject_id) REFERENCES subjects(id)
);

#exams
CREATE TABLE exams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    exam_date DATE
);

#marks
CREATE TABLE marks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject_id INT,
    exam_id INT,
    marks INT,
    FOREIGN KEY(student_id) REFERENCES students(id),
    FOREIGN KEY(subject_id) REFERENCES subjects(id),
    FOREIGN KEY(exam_id) REFERENCES exams(id)
);

#timetable
CREATE TABLE timetable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    subject_id INT,
    day VARCHAR(10),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY(subject_id) REFERENCES subjects(id)
);

#fees
CREATE TABLE fees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    amount DECIMAL(10,2),
    status VARCHAR(20),
    fee_type VARCHAR(50),
    due_date DATE,
    FOREIGN KEY(student_id) REFERENCES students(id)
);

#payments
CREATE TABLE payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fee_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    mode VARCHAR(20),
    FOREIGN KEY(fee_id) REFERENCES fees(id)
);

#notices
CREATE TABLE notices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200),
    description TEXT,
    created_date DATE
);

#SAMPLE DATA (IMPORTANT FOR DEMO)
INSERT INTO roles(name) VALUES ('ADMIN'), ('FACULTY'), ('STUDENT');

#CREATE ADMIN USER (IMPORTANT)
INSERT INTO users(username, password, enabled)
VALUES ('admin', '$2a$10$0Ao.jUTjlifivCH1bDLMzO0tTePHYAm10BUJVa1Dt9gsvBnmBXZ8e', true);

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1);
