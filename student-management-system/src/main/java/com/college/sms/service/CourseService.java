package com.college.sms.service;

import com.college.sms.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAll();
    void save(Course course);
}
