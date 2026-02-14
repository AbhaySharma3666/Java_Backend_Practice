package com.college.sms.service.impl;

import com.college.sms.entity.Enrollment;
import com.college.sms.repository.EnrollmentRepository;
import com.college.sms.service.EnrollmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Enrollment> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Enrollment enrollment) {
        repo.save(enrollment);
    }

    @Override
    public List<Enrollment> getByStudent(Integer studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
