package com.college.sms.service;

import com.college.sms.entity.Enrollment;
import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAll();
    void save(Enrollment enrollment);
    List<Enrollment> getByStudent(Integer studentId);
    void delete(Integer id);
}
