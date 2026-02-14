package com.college.sms.service;

import com.college.sms.entity.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> getAll();
    Subject getById(Integer id);
    void save(Subject subject);
    void delete(Integer id);
    List<Subject> getByCourse(Integer courseId);
}
