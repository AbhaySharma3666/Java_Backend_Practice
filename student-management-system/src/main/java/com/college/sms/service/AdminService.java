package com.college.sms.service;

import com.college.sms.entity.Student;
import com.college.sms.entity.User;

public interface AdminService {
    void createStudentUser(Student student, String password);
    User createUser(String username, String password, String role);
}
