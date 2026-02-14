package com.college.sms.service.impl;

import com.college.sms.entity.Course;
import com.college.sms.repository.CourseRepository;
import com.college.sms.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Course> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Course course) {
        repo.save(course);
    }
}
