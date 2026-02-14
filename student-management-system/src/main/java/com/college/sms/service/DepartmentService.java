package com.college.sms.service;

import com.college.sms.entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    void save(Department dept);
}
