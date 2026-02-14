package com.college.sms.service.impl;

import com.college.sms.entity.Department;
import com.college.sms.repository.DepartmentRepository;
import com.college.sms.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Department> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Department dept) {
        repo.save(dept);
    }
}
