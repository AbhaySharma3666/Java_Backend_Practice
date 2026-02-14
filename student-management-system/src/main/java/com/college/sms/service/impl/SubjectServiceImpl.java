package com.college.sms.service.impl;

import com.college.sms.entity.Subject;
import com.college.sms.repository.SubjectRepository;
import com.college.sms.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repo;

    public SubjectServiceImpl(SubjectRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Subject> getAll() {
        return repo.findAll();
    }

    @Override
    public Subject getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Subject subject) {
        repo.save(subject);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Subject> getByCourse(Integer courseId) {
        return repo.findByCourseId(courseId);
    }
}
